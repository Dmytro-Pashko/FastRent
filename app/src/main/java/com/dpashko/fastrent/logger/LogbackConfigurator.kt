package com.dpashko.fastrent.logger

import android.content.Context
import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.android.LogcatAppender
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.filter.ThresholdFilter
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.Appender
import ch.qos.logback.core.FileAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy
import org.slf4j.LoggerFactory
import java.io.File

/**
 * Helps to configure all the aspects of the app's logging using logback.
 */
internal class LogbackConfigurator {

    companion object {
        private const val ERROR_FILE_NAME = "errors.log"
        private const val MAIN_FILE_NAME = "trace.%d{yyyy-MM-dd}.log"
        private const val LOG_RECORD_PATTERN = "%d %-5level [%thread] %logger{0}: %msg%n"
        private const val MAX_ROLLOVER_HISTORY_FILES = 4
        private const val DEBUG_LOG_LEVEL = "DEBUG"
        private val ROOT_LOGGER = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
    }

    /**
     * Configures application logging.
     * @param context An application context.
     */
    fun configureAppLogging(context: Context) {
        configureLogCatLogging()
//        configureFileLogging(context)
    }

    /**
     * Configures logback to forward all log events to the Android native logging system(LogCat).
     */
    private fun configureLogCatLogging() {
        val loggerContext = LoggerFactory.getILoggerFactory() as LoggerContext
        loggerContext.reset()

        val logcatAppender = configureLogcatAppender(loggerContext)
        ROOT_LOGGER.addAppender(logcatAppender)
        ROOT_LOGGER.level = Level.ALL
    }

    /**
     * Configures logging into files accessible for other applications.
     *
     * @param context The application context.
     */
    private fun configureFileLogging(context: Context) {
        val loggerContext = LoggerFactory.getILoggerFactory() as LoggerContext

        val errorLogFileAppender = configureErrorFileAppender(context, loggerContext)
        ROOT_LOGGER.addAppender(errorLogFileAppender)

        val mainLogFileAppender =
            configureLogFileAppender(context, loggerContext)
        ROOT_LOGGER.addAppender(mainLogFileAppender)
    }

    private fun configureErrorFileAppender(
        context: Context, loggerContext: LoggerContext
    ): Appender<ILoggingEvent> {
        val encoder = PatternLayoutEncoder()
        encoder.context = loggerContext
        encoder.pattern = LOG_RECORD_PATTERN
        encoder.setImmediateFlush(true)
        encoder.start()

        val filter = ThresholdFilter()
        filter.setLevel(Level.ERROR.levelStr)
        filter.context = loggerContext
        filter.start()

        val appender = FileAppender<ILoggingEvent>()
        appender.context = loggerContext

        val errorLogFile = File(getLoggingDir(context), ERROR_FILE_NAME)
        appender.file = errorLogFile.absolutePath
        appender.addFilter(filter)
        appender.isAppend = true
        appender.encoder = encoder
        appender.start()

        return appender
    }

    private fun configureLogFileAppender(
        context: Context,
        loggerContext: LoggerContext
    ): Appender<ILoggingEvent> {
        val appender = RollingFileAppender<ILoggingEvent>()
        appender.context = loggerContext
        appender.isAppend = true

        val filter = ThresholdFilter()
        filter.setLevel(DEBUG_LOG_LEVEL)
        filter.context = loggerContext
        filter.start()
        appender.addFilter(filter)

        val encoder = PatternLayoutEncoder()
        encoder.context = loggerContext
        encoder.pattern = LOG_RECORD_PATTERN
        encoder.setImmediateFlush(true)
        encoder.start()
        appender.encoder = encoder

        val logFile = File(getLoggingDir(context), MAIN_FILE_NAME)
        val rollingPolicy: TimeBasedRollingPolicy<*> = TimeBasedRollingPolicy<Any>()
        rollingPolicy.fileNamePattern = logFile.absolutePath
        rollingPolicy.maxHistory = MAX_ROLLOVER_HISTORY_FILES
        rollingPolicy.setParent(appender)
        rollingPolicy.context = loggerContext
        rollingPolicy.start()
        appender.rollingPolicy = rollingPolicy

        appender.start()
        return appender
    }

    private fun getLoggingDir(context: Context): File? {
        // Ensure all the dirs in the path are created prior to start the logging.
        // Otherwise the logback will fail to create the log file.
        val loggingDir = context.getExternalFilesDir(null)
        loggingDir?.mkdirs()

        return loggingDir
    }

    private fun configureLogcatAppender(loggerContext: LoggerContext): LogcatAppender {
        val encoder = PatternLayoutEncoder()
        encoder.context = loggerContext
        encoder.pattern = "[%thread] %msg%n"
        encoder.start()

        val appender = LogcatAppender()
        appender.context = loggerContext
        appender.encoder = encoder
        appender.start()

        return appender
    }
}
