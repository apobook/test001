package com.thh.demo2;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.junit.Test;

public class Test001 {

	@Test
	public void getTest() {
		/*
		 * long maxMemory = Runtime.getRuntime().maxMemory()/1024/1024;
		 * System.out.println(maxMemory); //byte[] b = new byte[618587640];//600M String
		 * a = ""; for(int i = 0;i< 200;i++) { a+= i + ","; } String substring =
		 * a.substring(0, a.length()-1); Byte.valueOf("31"); String[] split =
		 * substring.split(","); List<Byte> op = new ArrayList<>(); Stream<String>
		 * stream = Arrays.stream(split); stream.parallel().map((x) -> { return
		 * Byte.valueOf(x); }).forEach(e -> { op.add(e); }); for (byte c : op) {
		 * System.out.println(c); } int i = 1024*1024*1024; System.out.println(i); long
		 * totalMemory = Runtime.getRuntime().totalMemory()/1024/1024;
		 * System.out.println(totalMemory);
		 */

	}

	@Test
	public void test2() {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");

		TemporalAccessor parse = df.parse("20180502");
		LocalDate from = LocalDate.from(parse);
		System.out.println(from.format(df));

		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);
	}

	@Test
	public void test3() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 100000; i++) {
			ImproHugeStr h = new ImproHugeStr();
			list.add(h.getSubString(1, 5));
		}
	}

	static class HugeStr {
		private String str = new String(new char[100000]);

		public String getSubString(int begin, int endIndex) {
			return str.substring(begin, endIndex);
		}
	}

	static class ImproHugeStr {
		private String str = new String(new char[100000]);

		public String getSubString(int begin, int endIndex) {
			return new String(str.substring(begin, endIndex));
		}
	}

	@Test
	public void test4() {
		String str = "0";
		Instant now = Instant.now();
		for (int j = 0; j < 10000; j++) {
			str += j;
		}
		Instant now2 = Instant.now();
		System.out.println(Duration.between(now, now2).toMillis());
		StringBuilder bu = new StringBuilder("0");
		for (int i = 0; i < 10000; i++) {
			bu.append(i);
		}
		Instant now3 = Instant.now();
		System.out.println(Duration.between(now2, now3).toMillis());
	}

	@Test
	public void test5() {
		String orgStr = null;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 1000; i++) {
			sb.append(i).append(";");
		}
		orgStr = sb.toString();
		Instant now = Instant.now();
		for (int i = 0; i < 100000; i++) {// split方法分割字符串效率比较低.
			orgStr.split(";");
		}
		Instant deiwd = Instant.now();
		System.out.println(Duration.between(now, deiwd).toMillis());
		StringTokenizer st = new StringTokenizer(orgStr, ";");
		for (int i = 0; i < 100000; i++) {// 比split快一点.
			while (st.hasMoreTokens()) {
				st.nextToken();
			}
			st = new StringTokenizer(orgStr, ";");
		}
		Instant now3 = Instant.now();
		System.out.println(Duration.between(deiwd, now3).toMillis());
		String temp = orgStr;
		for (int i = 0; i < 100000; i++) {// 在String类中的高效方法 indexOf() charAt() ,此种方式最快.
			while (true) {
				String splitStr = null;
				int j = temp.indexOf(";");
				if (j < 0)
					break;
				splitStr = temp.substring(0, j);
				temp = temp.substring(j + 1);
			}
		}
		Instant now4 = Instant.now();
		System.out.println(Duration.between(now3, now4).toMillis());

	}
}
