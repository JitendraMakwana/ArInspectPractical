package com.app.arinspect.arinspectpracticaldemo.dataModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
/**
 * @author Jitendra Makwana
 * @created on 15-Sept-19.
 */
class JsonResponse {
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("rows")
    @Expose
    var rows: List<Rows>? = null
}