package com.example.waproject

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle

import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.waproject.services.MyNotifiService
import com.example.waproject.ui.CustomToolbar
import com.example.waproject.ui.LazyListExample
import com.example.waproject.ui.LazyListString
import com.example.waproject.ui.SaveDataViewModel
import com.example.waproject.ui.theme.WaprojectTheme
import com.example.waproject.utils.isNotificationListenerEnabled
import com.google.firebase.FirebaseApp
import com.google.firebase.database.ChildEventListener
import com.google.firebase.ktx.Firebase
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database

class MainActivity : ComponentActivity() {

  private  val  saveDataViewModel: SaveDataViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this);
//        val config = RealmConfiguration.create(schema = setOf(SaveCustomeMessage::class))
//        val realm: Realm = Realm.open(config)

       // val list = realm.query<SaveCustomeMessage>(SaveCustomeMessage::class)
        // Write a message to the database
        val database = Firebase.database
       // val myRef = database.getReference("listData")


        var listMsg = saveDataViewModel.retrievData()
//        myRef.setValue(listMsg.get(0).expectedMessage)
      //  saveDataViewModel = ViewModelProvider(this)[SaveDataViewModel::class.java]
        var newFireItemList =  mutableListOf<String?>()
       lifecycleScope.launch {
           lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
               saveDataViewModel.notificationData.collect{
                   listMsg = saveDataViewModel.retrievData()
               }

           }
       }


        setContent {
            WaprojectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        CustomToolbar(title = "WA", this@MainActivity)
                      //  if (listMsg == null) {
                        var itemList = remember { mutableStateListOf<String?>() }
                        LazyListString(itemList)
                        // Call the function to fetch the updated list (e.g., in onActive or onCommit)
                        LaunchedEffect(Unit) {
                            saveDataViewModel.getNewMsgListFirebase { newList ->
                                // Update the state variable with the retrieved list
                                itemList = newList
                            }
                        }



//                        value?.let { Text(text = it) }
//                        } else {
//                            LazyListExample(listMsg)
//                        }
                    }

                    Greeting("Android")

                       DialogExamples()


//                        val settingsIntent =
//                            Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
//                       startActivity(settingsIntent)
                   // }
                }
            }
        }

        val intent = Intent(this@MainActivity, MyNotifiService::class.java)
        startService(intent)


    }




@Composable
fun DialogExamples() {
    if (!isNotificationListenerEnabled(this,this.packageName) && !hasNotificationAccess(this)) {

        // ...
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Provide access to read Notification")
            },
            text = {
                Text(
                    "ReadNotification will be able to read all notifications, including personal information such as" +
                            " contact names and test of message you receive. This app will also be able to snooze or dismiss notifications or " +
                            "take action on buttons in notifications, including answering phone calls.",

                    )
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        onClick = { openDialog.value = false }
                    ) {
                        Text("Dismiss")
                    }
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp),
                        onClick = { openNotificationAccess()
                            openDialog.value = false}
                    ) {
                        Text("OK")
                    }
                }
            }
        )
    }
}
}

    private fun openNotificationAccess() {

        //Open listener reference message // Notification access
        val intent_s = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
        } else {
            TODO("VERSION.SDK_INT < LOLLIPOP_MR1")
        }
        startActivityForResult(intent_s, 100)
    }

//     fun writeMsgData(msg: String){
//        if (saveDataViewModel == null ){
//            saveDataViewModel = ViewModelProvider(this).get(SaveDataViewModel::class.java)
//            saveDataViewModel.saveData(msg)
//        } else {
//            saveDataViewModel.saveData(msg)
//        }
//    }
}

private fun hasNotificationAccess(context: Context): Boolean {
    return Settings.Secure.getString(
        context.applicationContext.contentResolver,
        "enabled_notification_listeners"
    ).contains(context.applicationContext.packageName)
}


//private val isNotificationServiceRunning: Boolean
//    get() {
//
//        val contentResolver: ContentResolver = this.getContentResolver()
//        val enabledNotificationListeners = Settings.Secure.getString(contentResolver, "enabled_notification_listeners")
//        val packageName = this.getPackageName
//        return enabledNotificationListeners != null && enabledNotificationListeners.contains(packageName)
//    }
//
//


@Composable
fun Greeting(name: String) {
   // Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WaprojectTheme {
        Greeting("Android")
    }
}