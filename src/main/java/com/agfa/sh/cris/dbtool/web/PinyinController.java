package com.agfa.sh.cris.dbtool.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

@RestController
public class PinyinController {

	@RequestMapping("/pinyin")
	public String pinyin(@RequestParam(name="source", required=true) String source, @RequestParam(name="space", required=false) Boolean space) {
		if (source != null && !source.isEmpty()) {
			HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
			outputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
			outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
			outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
			
			List<String> pinyins = new ArrayList<String>();
			int length = source.length();
			
			try {
				for(int i=0; i<length; i++) {
					String[] pys = PinyinHelper.toHanyuPinyinStringArray(source.charAt(i), outputFormat);
					if (pys != null && pys.length > 0) {
						pinyins.add(pys[0]);
					} else {
						pinyins.add(String.valueOf(source.charAt(i)));
					}
				}
				String delim = "";
				if (space != null && space == true) {
					delim = " ";
				}
				return StringUtils.arrayToDelimitedString(pinyins.toArray(new String[0]), delim);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
