/*
 * Copyright © 2019 Marc Auberer. All rights reserved.
 */

package com.mrgames13.jimdo.feinstaubapp.ui.viewmodel

import android.content.Context

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.mrgames13.jimdo.feinstaubapp.tool.StorageUtils

class ClusterRenderer(context: Context, map: GoogleMap, clusterManager: ClusterManager<SensorClusterItem>, private val su: StorageUtils) : DefaultClusterRenderer<SensorClusterItem>(context, map, clusterManager) {
    override fun onBeforeClusterItemRendered(item: SensorClusterItem, markerOptions: MarkerOptions) {
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(
            when {
                su.isFavouriteExisting(item.title) -> BitmapDescriptorFactory.HUE_RED
                su.isSensorExisting(item.title) -> BitmapDescriptorFactory.HUE_GREEN
                else -> BitmapDescriptorFactory.HUE_BLUE
            }
        ))
    }
}
