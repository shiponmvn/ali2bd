package com.aliexpressshoppingbd.ali2bd.core

sealed class NetworkState{

   data object Good: NetworkState()

   data object Failed: NetworkState()

}
