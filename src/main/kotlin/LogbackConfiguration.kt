import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.spi.Configurator
import ch.qos.logback.core.spi.ContextAwareBase

class LogbackConfiguration : ContextAwareBase(), Configurator {
    override fun configure(context: LoggerContext?): Configurator.ExecutionStatus? {
        TODO("Not yet implemented")
    }
}