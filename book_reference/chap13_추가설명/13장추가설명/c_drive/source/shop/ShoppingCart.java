package kr.co.hanbitbook.ejb.examples.shop;

public class ShoppingCart {
  private java.util.Collection list = null;
  public ShoppingCart(){
    list = new java.util.ArrayList();
  } // ShoppingCart

  public java.util.Collection getItems(){
        if(list.size() == 0)
                return null;
        return list;
  }

  public void addItem(ItemDataBean ibean){
    java.util.Iterator iter = list.iterator();
    boolean findflag = false;
    // �̹� ����īƮ�� ������ ��� ������ ����
    while(iter.hasNext()){
      ItemDataBean temp = (ItemDataBean)iter.next();
      if(temp.getId().equals(ibean.getId())){
        temp.setCount(temp.getCount() + ibean.getCount());
        findflag = true;
        break;
      }
    } // while

    // ����īƮ�� �������� ���� ��� �߰�
    if(!findflag){
      list.add(ibean);
    }
  } // addItem

  public void deleteItem(String id){
    java.util.Iterator iter = list.iterator();
    boolean findflag = false;
    // �̹� ����īƮ�� ������ ��� �ش� ��ǰ ����
    while(iter.hasNext()){
      ItemDataBean temp = (ItemDataBean)iter.next();
      if(temp.getId().equals(id)){
        list.remove(temp);
        break;
      }
    } // while
  } // deleteItem

  public void updateItem(String id, int count){
    java.util.Iterator iter = list.iterator();
    boolean findflag = false;
    // �̹� ����īƮ�� ������ ��� �ش� ��ǰ�� ������ ����
    while(iter.hasNext()){
      ItemDataBean temp = (ItemDataBean)iter.next();
      if(temp.getId().equals(id)){
        temp.setCount(count);
        break;
      }
    } // while
  } // deleteItem
}
