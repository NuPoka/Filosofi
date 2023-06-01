import kotlin.random.Random
fun main(args: Array<String>) {
    var n:Int
    var startN: Int

    do{
        n = input(Int,"Выберите кол-во философов >0 : ") as Int
    }while(n < 0)

    do{
        startN = (input(Int,"Какой философ будет первым выбирать: ") as Int)-1
    }while(startN < 0 || startN > n)
    var thinkers = MutableList(n) {Thinker(input(String,"Введите имя ${it+1}-го философа: ") as String)}

    for(i in 1..startN){
        var x = thinkers[0]
        for (j in 1 until thinkers.size) {
            thinkers[j-1] = thinkers[j]
        }
        thinkers[n-1] = x
    }
    thinkers[0].toolLRN = Random.nextBoolean()
    var helper = thinkers[0].toolLRN
    println("Философ ${thinkers[0].name} ${if(helper==null) "размышляет, вилки" else "ест, вилка"}" +
            " ${if(helper == false) "слева" else if(helper == true) "справа" else "нет"}")
    thinkers.add(thinkers[0])
    for (i in 1 until thinkers.size-1){
        when(Random.nextBoolean()){
            false -> thinkers[i].toolLRN = if(thinkers[i-1].toolLRN!=true) false
            else if(thinkers[i+1].toolLRN!=false) true
            else null
            true -> thinkers[i].toolLRN = if(thinkers[i+1].toolLRN!=false) true
            else if(thinkers[i-1].toolLRN!=true) false
            else null
        }
        helper = thinkers[i].toolLRN
        println("Философ ${thinkers[i].name} ${if(helper==null) "размышляет, вилки" else "ест, вилка"}" +
                " ${if(helper == false) "слева" else if(helper == true) "справа" else "нет"}")
    }



}
class Thinker(Nm:String = "Неизвестно"){
    var name = Nm
    var toolLRN: Boolean? = null
}
