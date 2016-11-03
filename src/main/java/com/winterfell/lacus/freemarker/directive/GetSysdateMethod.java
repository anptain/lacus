package com.winterfell.lacus.freemarker.directive;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class GetSysdateMethod implements TemplateMethodModelEx {

	public Object exec(List args) throws TemplateModelException {
		// 得到函数第一个参数,得到的字符串两头会有引号,所以replace
		String datePattern = (args.get(0).toString()).replace("'", "");

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);

		return sdf.format(date);
	}
}