package com.jubiman.scourgemod.util;

import necesse.engine.GameLog;

public class Logger {
	public static void info(String message, Object... args) {
		GameLog.out.format("[ScourgeMod] %s\n", String.format(message, args));
	}

	public static void error(String message, Object... args) {
		GameLog.err.format("[ScourgeMod] %s\n", String.format(message, args));
	}

	public static void warn(String message, Object... args) {
		GameLog.warn.format("[ScourgeMod] %s\n", String.format(message, args));
	}

	public static void debug(String message, Object... args) {
		GameLog.debug.format("[ScourgeMod] %s\n", String.format(message, args));
	}

	public static void file(String message, Object... args) {
		GameLog.file.format("[ScourgeMod] %s\n", String.format(message, args));
	}
}
