package com.rer.buildsrc

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

fun DependencyHandler.uiModule() {
    androidUiPack()
    androidxLifecycle()
    androidTest()
}

fun DependencyHandler.featureModule() {
    uiModule()
    coroutines()
    hilt()
    implementation(project(":core"))
}


fun DependencyHandler.androidUiPack() {
    implementation(Deps.coreKtx)
    implementation(Deps.composeUi)
    implementation(Deps.composeUiTooling)
    implementation(Deps.composeUiToolingPreview)
    implementation(Deps.composeMaterial3)
    implementation(Deps.composeMaterial3WindowSize)
    implementation(Deps.activityCompose)
    implementation(Deps.composeIconsExtended)
    implementation(Deps.composeConstraintLayout)
    implementation(Deps.composeRuntime)
    implementation(Deps.composeReimagined)
}
fun DependencyHandler.room(){
    implementation(Deps.roomRuntime)
    annotationProcessor(Deps.roomCompiler)
    kapt(Deps.roomCompiler)
    implementation(Deps.roomKtx)
}
fun DependencyHandler.lottie() {
    implementation(Deps.lottie)
}

fun DependencyHandler.androidTest() {
    testImplementation(Deps.testRunner)
    testImplementation(Deps.jUnit)
    testImplementation(Deps.jupiter)
    testImplementation(Deps.googleTruth)
    androidTestImplementation(Deps.composeTesting)
    testImplementation(Deps.rules)
    debugImplementation(Deps.composeTestManifest)
    testImplementation(Deps.coroutineTest)
    testImplementation(Deps.mockk)
    testImplementation(Deps.jUnit5)
    testRuntimeOnly(Deps.jUnit5Engine)
    testRuntimeOnly(Deps.jUnit5Vintage)
    testImplementation(Deps.cashAppTurbine)
}
fun DependencyHandler.androidxLifecycle() {
    implementation(Deps.lifecycleRuntime)
    implementation(Deps.lifecycleRuntimeCompose)
    implementation(Deps.lifecycleViewModel)
    implementation(Deps.lifecycleCommon)
    implementation(Deps.lifecycleLiveData)
    implementation(Deps.workRuntime)
}

fun DependencyHandler.coroutines() {
    implementation(Deps.coroutineCore)
    implementation(Deps.coroutineAndroid)
}
fun DependencyHandler.hilt() {
    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltCompiler)
    implementation(Deps.hiltNavCompose)
}
fun DependencyHandler.implementation(depName: Any) {
    add("implementation", depName)
}

fun DependencyHandler.annotationProcessor(depName: Any) {
    add("annotationProcessor", depName)
}

private fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
}

private fun DependencyHandler.compileOnly(depName: String) {
    add("compileOnly", depName)
}

private fun DependencyHandler.api(depName: String) {
    add("api", depName)
}

private fun DependencyHandler.testImplementation(depName: String) {
    add("testImplementation", depName)
}

private fun DependencyHandler.androidTestImplementation(depName: String) {
    add("androidTestImplementation", depName)
}

private fun DependencyHandler.debugImplementation(depName: String) {
    add("debugImplementation", depName)
}

private fun DependencyHandler.releaseImplementation(depName: String) {
    add("releaseImplementation", depName)
}

private fun DependencyHandler.testRuntimeOnly(depName: String) {
    add("testRuntimeOnly", depName)
}