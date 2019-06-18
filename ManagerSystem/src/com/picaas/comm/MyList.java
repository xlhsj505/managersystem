package com.picaas.comm;

/**

 * �Զ��峤�ȿɱ�����

 * 

 * @author Administrator

 * 

 */

public class MyList {

	// ����һ����ʼ����Ϊ0�����飬������������

	private String[] src = new String[0];

	// ����

	public void add(String s) {

		//���������飬������ԭ���鳤��+1

		String[] dest = new String[src.length+1];

		//��ԭ��������ݿ�����������

		System.arraycopy(src, 0, dest, 0, src.length);

		//����Ԫ�طŵ�dest�����ĩβ

		dest[src.length]=s;

		//��srcָ��dest

		src=dest;

	}

	// �޸�ָ��λ�õ�Ԫ��

	public void modify(int index, String s) {

		src[index]=s;

	}

	// ɾ��ָ��λ�õ�Ԫ��

	public void delete(int index) {

		String[] dest = new String[src.length-1];

		//��ԭ��������ݿ�����������

		System.arraycopy(src, 0, dest, 0, index);

		System.arraycopy(src, index+1, dest, index, src.length-1-index);

		src=dest;

	}

	// ���ָ��λ�õ�Ԫ��

	public String get(int index) {

		return src[index];

	}

	// ��ָ��λ�ò���ָ��Ԫ��

	public void insert(int index, String s) {

		//���������飬������ԭ���鳤��+1

		String[] dest = new String[src.length+1];

		//��ԭ��������ݿ�����������

		System.arraycopy(src, 0, dest, 0, index);

		dest[index]=s;

		System.arraycopy(src, index, dest, index+1, src.length-index);

		src=dest;

		

	}

	// ���Ԫ�ظ���

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

		System.out.println("����֮ǰ��");

		m.print();

		m.insert(2,"22");

		System.out.println("����֮��");

		m.print();	

	}

 

}

