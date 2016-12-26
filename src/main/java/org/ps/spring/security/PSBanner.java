package org.ps.spring.security;

import java.io.PrintStream;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

public class PSBanner implements Banner {

	@Override
	public void printBanner(Environment env, Class<?> clazz, PrintStream out) {
		out.append("                 _                 _       _                   \n");
		out.append(" _ __   ___  ___| |_ ___  ___ _ __(_)_ __ | |_ _   _ _ __ ___  \n");
		out.append("| '_ \\ / _ \\/ __| __/ __|/ __| '__| | '_ \\| __| | | | '_ ` _ \\ \n");
		out.append("| |_) | (_) \\__ \\ |_\\__ \\ (__| |  | | |_) | |_| |_| | | | | | |\n");
		out.append("| .__/ \\___/|___/\\__|___/\\___|_|  |_| .__/ \\__|\\__,_|_| |_| |_|\n");
		out.append("|_|                                 |_|                        \n");		
	}
		
}
