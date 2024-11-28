import java.time.*
import java.time.Duration

val yesterdayStart = LocalDate.now().minusDays(1).atStartOfDay()
val today = LocalDateTime.now()
val com = today>yesterdayStart
println(yesterdayStart)
println(today)

val date1 = LocalDateTime.of(2024,11,27,12,30)
//val diff = today.minus(date1)
//println(diff.hour)
val diff2 = Duration.between(date1, today)
print(diff2.toMinutes())


