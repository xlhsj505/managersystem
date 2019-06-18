package com.picaas.comm;

/**

 * 自定义长度可变数组

 * 

 * @author Administrator

 * 

 */

public class MyList {

	// 定义一个初始长度为0的数组，用来缓存数据

	private String[] src = new String[0];

	// 增加

	public void add(String s) {

		//定义新数组，长度是原数组长度+1

		String[] dest = new String[src.length+1];

		//将原数组的数据拷贝到新数组

		System.arraycopy(src, 0, dest, 0, src.length);

		//将新元素放到dest数组的末尾

		dest[src.length]=s;

		//将src指向dest

		src=dest;

	}

	// 修改指定位置的元素

	public void modify(int index, String s) {

		src[index]=s;

	}

	// 删除指定位置的元素

	public void delete(int index) {

		String[] dest = new String[src.length-1];

		//将原数组的数据拷贝到新数组

		System.arraycopy(src, 0, dest, 0, index);

		System.arraycopy(src, index+1, dest, index, src.length-1-index);

		src=dest;

	}

	// 获得指定位置的元素

	public String get(int index) {

		return src[index];

	}

	// 在指定位置插入指定元素

	public void insert(int index, String s) {

		//定义新数组，长度是原数组长度+1

		String[] dest = new String[src.length+1];

		//将原数组的数据拷贝到新数组

		System.arraycopy(src, 0, dest, 0, index);

		dest[index]=s;

		System.arraycopy(src, index, dest, index+1, src.length-index);

		src=dest;

		

	}

	// 获得元素个数

	public int size() {

		return src.length;

	}

	

	public void print()

	{

		for(int i=0;i<size();i++)

			System.out.println(src[i]);

	}

	

	public static void main(String[] args)

	{

		MyList m=new MyList();

		m.add("15");

		m.add("16");

		m.add("17");

		m.add("18");

		m.add("19");

		System.out.println("插入之前：");

		m.print();

		m.insert(2,"22");

		System.out.println("插入之后：");

		m.print();	

	}

 

}

