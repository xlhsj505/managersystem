package com.picaas.comm;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

public class JayCommonUtil {

	/**
     * ��ּ���
     * @param <T>
     * @param resList  Ҫ��ֵļ���
     * @param count    ÿ�����ϵ�Ԫ�ظ���
     * @return  ���ز�ֺ�ĸ�������
     */
    public static  <T> List<List<T>> split(List<T> resList, int count){
        
        if(resList==null ||count<1)
            return  null ;
        List<List<T>> ret=new ArrayList<List<T>>();
        int size=resList.size();
        if(size<=count){ //����������countָ���Ĵ�С
            ret.add(resList);
        }else{
            int pre=size/count;
            int last=size%count;
            //ǰ��pre�����ϣ�ÿ����С����count��Ԫ��
            for(int i=0;i<pre;i++){
                List<T> itemList=new ArrayList<T>();
                if (last != 0 && i == pre-1) {
					for (int k=0;k<count+last;k++){
						itemList.add(resList.get(i*count+k));
					}
				} else {
					for(int j=0;j<count;j++){
	                    itemList.add(resList.get(i*count+j));
	                }
	                
				}
                ret.add(itemList);
            }
//            //last�Ľ��д���
//            if(last>0){
//                List<T> itemList=new ArrayList<T>();
//                for(int i=0;i<last;i++){
//                    itemList.add(resList.get(pre*count+i));
//                }
//                ret.add(itemList);
//            }
        }
        return ret;
        
    }

}
