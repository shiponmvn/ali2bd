package com.aliexpressshoppingbd.ali2bd

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform