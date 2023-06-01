// поддерживает как типы, так и значения, вроде input(Int) или input(4)
fun input(type:Any = String , text:String = ""): Any{
    //Определение названия типа type
    val typeName = type.javaClass.name.split(".")
    val className = if(typeName[2] == "internal")
        typeName[3].split("C")[0]
    else typeName[2]
    //Ввод
    var output:Any? = null
    var attempts = 0
    do {
        print(text)
        var buf = readln()
        //Приводим к найденному типу type и преобразуем
        output = when (className) {
            "String" -> buf
            "Int", "Integer" -> buf.toIntOrNull()
            "Float" -> buf.replace(",",".").toFloatOrNull()
            "Double" -> buf.replace(",",".").toDoubleOrNull()
            else -> throw Exception("NotSup: функция input() не поддерживает тип ${type.javaClass.name}")
        }
        //Вызов ошибки при четвёртом неуспешном вводе
        if(attempts++==3)
            throw Exception("NumOfAttExc: Количество попыток ввода превышено")
        //Выводим сообщение о провале
        if(output==null)
            println("Введённые данные некорректны, введите их в соответствии с типом $className")
    }while(output==null)
    return output
}