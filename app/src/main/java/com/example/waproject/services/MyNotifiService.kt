package com.example.waproject.services

import android.annotation.SuppressLint
import android.app.Notification
import android.content.Intent
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import android.widget.Toast
import com.example.waproject.eventBus.NotificationEventBus
import com.example.waproject.models.SaveCustomeMessage
import com.example.waproject.ui.SaveDataViewModel
import com.example.waproject.utils.NotificationUtils
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.query.RealmResults
import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("OverrideAbstract")
class MyNotifiService : NotificationListenerService() {
    private var bw: BufferedWriter? = null
    private var sdf: SimpleDateFormat? = null
    private val handler = MyHandler()
    private var nMessage: String? = null
    private var data: String? = null
    private val realm: Realm? = null
    var list: RealmResults<SaveCustomeMessage>? = null
    var mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            val msgString = msg.obj as String
            Toast.makeText(applicationContext, msgString, Toast.LENGTH_LONG).show()
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.i("Suyash", "Service is started" + "-----")
        if (intent != null && intent.hasExtra("data")) data = intent.getStringExtra("data")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        super.onNotificationRemoved(sbn)
        Log.i(TAG, "onNotificationRemoved")
    }


    fun workWithViewModel(saveDataViewModel: SaveDataViewModel){

    }
    override fun onNotificationPosted(sbn: StatusBarNotification) {



        val action = NotificationUtils.getQuickReplyAction(sbn.notification, packageName)
        if (action !=null){
            if (sbn.packageName.equals("com.whatsapp", ignoreCase = true)){
                val notificationData = sbn.notification.extras.getString(Notification.EXTRA_TEXT)
                NotificationEventBus.sendNotificationData(notificationData)
            }
        }

//        val config = RealmConfiguration.create(schema = setOf(SaveCustomeMessage::class))
//        val realm: Realm = Realm.open(config)
        Log.i(TAG, "Here")
       // this@MyNotifiService.cancelNotification(sbn.key)
//        val action = NotificationUtils.getQuickReplyAction(sbn.notification, packageName)
//
//        if (action != null) {
//            Log.i(TAG, "success")
//            if (sbn.packageName.equals("com.whatsapp", ignoreCase = true)) {
//
//
//                val msg = sbn.notification.extras.getString("android.text")
//               // MainActivity().writeMsgData(msg!!)
//                realm.writeBlocking {
//                    copyToRealm(SaveCustomeMessage().apply {
//                       expectedMessage=  msg!!
//                    })
//                }
//
//                // list = realm.where(SaveCustomeMessage.class).equalTo("expectedMessage",msg).findAll();
//
//                //   if (msg != null && !msg.equalsIgnoreCase( "📷 Photo")) {
//                // if (realm.where(SaveCustomeMessage.class).equalTo("expectedMessage", msg).findAll().isValid())
//                //     list = realm.where(SaveCustomeMessage.class).equalTo("expectedMessage", msg).findAll();
//                //   if (!list.isEmpty())
//                //       action.sendReply(getApplicationContext(), list.get(0).getReplyMessage());
//                //  }
//
//                // action.sendReply(getApplicationContext(), "This is bot of suyash . When suyash see this msg he'll reply to you. I am not trained well I am in training");
//            }
//        } else {
//            Log.i(TAG, "not success")
//        }
        try {
            //
            //Some notifications can't parse the TEXT content. Here is a message to judge.
            if (sbn.notification.tickerText != null) {
                val sp = getSharedPreferences("msg", MODE_PRIVATE)
                nMessage = sbn.notification.tickerText.toString()
                Log.e("KEVIN", "Get Message-----$nMessage")
                sp.edit().putString("getMsg", nMessage).apply()
                val obtain = Message.obtain()
                obtain.obj = nMessage
                mHandler.sendMessage(obtain)
                init()
                if (nMessage!!.contains(data!!)) {
                    val message = handler.obtainMessage()
                    message.what = 1
                    handler.sendMessage(message)
                    writeData(sdf!!.format(Date(System.currentTimeMillis())) + ":" + nMessage)
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this@MyNotifiService, "Unresolvable notification", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun writeData(str: String) {
        try {
//            bw.newLine();
//            bw.write("NOTE");
            bw!!.newLine()
            bw!!.write(str)
            bw!!.newLine()
            //            bw.newLine();
            bw!!.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }



    private fun init() {
        // realm = Realm.getDefaultInstance();
//        val config = RealmConfiguration.create(schema = setOf(SaveCustomeMessage::class))
//        val realm: Realm = Realm.open(config)


        //  list = ArrayList(realm.where(SaveCustomeMessage::class.java));

        sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        try {
            val fos = FileOutputStream(newFile(), true)
            val osw = OutputStreamWriter(fos)
            bw = BufferedWriter(osw)
        } catch (e: IOException) {
            Log.d("KEVIN", "BufferedWriter Initialization error")
        }
        Log.d("KEVIN", "Initialization Successful")
    }

    private fun newFile(): File {
        val fileDir =
            File(Environment.getExternalStorageDirectory().path + File.separator + "ANotification")
        fileDir.mkdir()
        val basePath = Environment.getExternalStorageDirectory()
            .toString() + File.separator + "ANotification" + File.separator + "record.txt"
        return File(basePath)
    }

    internal inner class MyHandler : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                1 -> {}
            }
        }
    }

    companion object {
        const val TAG = "Suyash"
    }
}