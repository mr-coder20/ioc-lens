package io.github.mrcoder20.ioclens

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform