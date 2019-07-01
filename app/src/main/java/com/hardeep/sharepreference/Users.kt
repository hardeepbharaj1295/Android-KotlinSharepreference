package com.hardeep.sharepreference

import java.math.BigInteger

class Users {
    var name: String? = null
    var email: String? = null
    var number: Long? = null
    var country: String? = null
    var password: String? = null
    var gender: String? = null

    constructor(name: String,email: String,number: Long,country: String,password: String,gender: String)
    {
        this.name = name
        this.email = email
        this.number = number
        this.country = country
        this.password = password
        this.gender = gender
    }
}