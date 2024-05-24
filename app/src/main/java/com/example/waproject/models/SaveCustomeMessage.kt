package com.example.waproject.models;

import android.os.Parcel
import android.os.Parcelable
import io.realm.kotlin.types.RealmObject


open class SaveCustomeMessage( var expectedMessage: String?= null,
                               var replyMessage: String?= null) : RealmObject {
                                   constructor():this("")

// constructor(parcel: Parcel) : this(
//         parcel.readString(),
//         parcel.readString()) {
// }
//
// override fun writeToParcel(parcel: Parcel, flags: Int) {
//  parcel.writeString(expectedMessage)
//  parcel.writeString(replyMessage)
// }
//
// override fun describeContents(): Int {
//  return 0
// }
//
// companion object CREATOR : Parcelable.Creator<SaveCustomeMessage> {
//  override fun createFromParcel(parcel: Parcel): SaveCustomeMessage {
//   return SaveCustomeMessage(parcel)
//  }
//
//  override fun newArray(size: Int): Array<SaveCustomeMessage?> {
//   return arrayOfNulls(size)
//  }
// }
}