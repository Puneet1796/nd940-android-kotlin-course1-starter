package com.udacity.shoestore.models

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Shoe(
    @get:Bindable
    var name: String = "",
    @get:Bindable
    var size: Double = 0.0,
    @get:Bindable
    var company: String = "",
    @get:Bindable
    var description: String = "",
    @get:Bindable
    val images: MutableList<String> = mutableListOf("")
) : BaseObservable(), Parcelable