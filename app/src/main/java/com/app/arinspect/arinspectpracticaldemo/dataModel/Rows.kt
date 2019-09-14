package com.app.arinspect.arinspectpracticaldemo.dataModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Jitendra Makwana
 * @created on 15-Sept-19.
 */
class Rows {
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("imageHref")
    @Expose
    var imageHref: String? = null

}