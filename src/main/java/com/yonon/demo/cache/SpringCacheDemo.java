package com.yonon.demo.cache;

/**
 * Created by jr-jiangyinghan on 2017-8-31.
 */
public class SpringCacheDemo {
    public static void main(String[] args) {

//        EmployeeDao employeeDao = new EmployeeDao();
//        for (int i = 0; i < 2; i++) {
//            long startTime = System.currentTimeMillis();
//            Person employee1 = employeeDao.findEmployee("张三", 20);
//            long endTime = System.currentTimeMillis();
//            System.out.println("time->" + (i + 1) + " $ " + String.valueOf(endTime - startTime) + "::" + employee1.toString());
//            endTime = System.currentTimeMillis();
//            startTime = endTime;
//            Person employee2 = employeeDao.findEmployee("李四", 30);
//            endTime = System.currentTimeMillis();
//            System.out.println("time->" + (i + 1) + " $ " + String.valueOf(endTime - startTime) + "::" + employee2.toString());
//        }
        try {
            testGetAccountByName();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static AccountService accountService = new AccountService();


    public static void testGetAccountByName() throws Exception {
        accountService.getAccountByName("accountName");
        accountService.getAccountByName("accountName");

        accountService.reload();

        accountService.getAccountByName("accountName");
        accountService.getAccountByName("accountName");
    }

}
