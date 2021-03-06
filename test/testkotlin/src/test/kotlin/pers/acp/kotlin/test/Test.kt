package pers.acp.kotlin.test

import kotlinx.coroutines.*
import org.apache.commons.text.CharacterPredicates
import org.apache.commons.text.RandomStringGenerator
import org.springframework.expression.spel.standard.SpelExpressionParser
import pers.acp.core.security.Md5Encrypt
import java.io.File
import java.util.*


/**
 * Create by zhangbin on 2017-12-19 11:28
 */
fun main(args: Array<String>) {
    println("Hello World!")

    val generator: RandomStringGenerator = RandomStringGenerator.Builder()
            .withinRange(33, 126)
            .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
            .build()
    val s1 = generator.generate(32)
    val s2 = generator.generate(16)
    println(s1)
    println(s2)

    println(System.currentTimeMillis())
    println(Date().time)

    val cls = Class.forName("com.fasterxml.jackson.module.kotlin.KotlinModule")
    println(cls)
    println(cls.canonicalName)

    val path = "C:\\WorkFile\\IdeaProjects\\pers-acp\\acp-admin-cloud\\logs\\log-server"
    val fileName = "..\\..\\log-server.log"
    val file = File("$path\\$fileName")
    println(file.exists())
    println(file.absolutePath)
    println(file.canonicalPath)
    println(file.length())

//    testSpEL()
//    val now = System.currentTimeMillis()
//    println(testKotlin())
//    println("总耗时：" + (System.currentTimeMillis() - now))

    var result = "肇牵车牛远服贾用孝养厥父母"
    for (i in 1..100000000) {
        result = Md5Encrypt.encrypt(result)
    }
    println(result)
}

/**
 * 测试 SpringEL 表达式
 */
fun testSpEL() {
    val path = "C:\\WorkFile\\IdeaProjects\\pers-acp\\acp-admin-cloud\\logs\\log-server"
    val fileName = "..\\..\\log-server.log"
    val file = File("$path\\$fileName")
    val parser = SpelExpressionParser()
    val exp1 = parser.parseExpression("canonicalPath")
    println("Expression1 Value: ${exp1.getValue(file)}")

    val testData = TestData()
    val exp2 = parser.parseExpression("test2=13")
    println("Expression2 Value: ${exp2.getValue(testData)}")
    println(testData)

    val number = 5
    val exp3 = parser.parseExpression("#this>=5 and #this<10?'C':'M1'")
    println("Expression3 Value: ${exp3.getValue(number, String::class.java)}")

    testData.test1 = "T10"
    val exp4 = parser.parseExpression("{'T1','T2','T3','T4'}.contains(test1)")
    println("Expression4 Value: ${exp4.getValue(testData, Boolean::class.java)}")

    val list = mutableListOf("1", "2", "3", "A", "B", "C")
    println(list)
    list.iterator().let {
        while (it.hasNext()) {
            val value = it.next()
            it.remove()
            println("value: $value")
        }
    }
    println(list)

    println(Thread.currentThread().hashCode())
}

fun testKotlin() = runBlocking {
    withContext(Dispatchers.IO) {
        val result = try {
            val job1 = async {
                delay(3000)
                println(1)
            }
            val job2 = async {
                delay(2000)
                println(2)
            }
//            job1.await()
//            job2.await()
            true
        } catch (e: Exception) {
            null
        }
        println("finished")
        result
    }
}