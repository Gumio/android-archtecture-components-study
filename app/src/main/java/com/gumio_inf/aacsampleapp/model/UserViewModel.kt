package com.gumio_inf.aacsampleapp.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class UserViewModel: ViewModel() {
    private var user: MutableLiveData<User>? = null

    fun getUser(): MutableLiveData<User>? {
        if (user == null) {
            user = MutableLiveData()
            load()
        }
        return user
    }

    private fun load() {
        user?.postValue(User("Gumioji", 23))
    }
}

data class User(val name: String, val age: Int)

// 従来のViewModel宣言
//class UserViewModelKari {
//    private var user: User? = null
//
//    fun getUser(): User? {
//        if (user == null) {
//            load()
//        }
//        return user
//    }
//
//    private fun load() {
//        user = User("Gumio", 23)
//    }
// }