package com.example.waproject.ui

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waproject.eventBus.NotificationEventBus
import com.example.waproject.models.SaveCustomeMessage
import com.example.waproject.services.MyNotifiService
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch

class SaveDataViewModel( ) :  ViewModel() {

    val notificationData : StateFlow<String?> = NotificationEventBus.notificationData

    // Variable to hold the saved notification data
    private var savedNotificationData: String? = null

    val config = RealmConfiguration.create(schema = setOf(SaveCustomeMessage::class))
    val realm: Realm = Realm.open(config)

    private val _listData = mutableStateOf<List<String>>(emptyList())
    val listData: State<List<String>> = _listData
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("listData")
    var newItemList = mutableListOf<String?>()
    init {
        observeNotificationData()
    }
    private fun observeNotificationData() {
        viewModelScope.launch {
            notificationData.collect { data ->
                savedNotificationData = data
                saveData(data)
               // myRef.setValue(data)
                val newItem = myRef.push()
                newItem.setValue(data).addOnSuccessListener {
                    Log.d("Firebase success", data.toString())
                }
                    .addOnFailureListener {
                        Log.d("Firebase Failur", data.toString())
                    }


            }
        }
    }
    fun saveData(message: String?) {
        realm.writeBlocking {
            copyToRealm(SaveCustomeMessage().apply {
                expectedMessage = message
            })
        }
    }


    fun getNewMsgListFirebase(callback :( SnapshotStateList<String?> ) -> Unit)  {
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val dataList = SnapshotStateList<String?>()
                for (childSnapshot in dataSnapshot.children) {
                    val item = childSnapshot.getValue(String::class.java)
                    item?.let { dataList.add(it) }
                }
                // Use dataList here (it contains the list of saved data)
                callback(dataList)

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
            }
        })
    }
    fun retrievData(): RealmResults<SaveCustomeMessage> {
          val _listData = mutableStateOf<List<String>>(emptyList())


        val list = realm.query<SaveCustomeMessage>(SaveCustomeMessage::class)
        var listMsg : RealmResults<SaveCustomeMessage> = list.find()
        return listMsg
    }
}