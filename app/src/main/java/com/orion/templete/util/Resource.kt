package com.orion.templete.util

sealed  class Resource<T>(val data:T?=null,val message:String?=null){
    class  Success<T>(data:T?): Resource<T>(data = data)
    class Loading<T>(message: String?) : Resource<T>()
    class Error<T>(error:String?) : Resource<T>(message= error)
}