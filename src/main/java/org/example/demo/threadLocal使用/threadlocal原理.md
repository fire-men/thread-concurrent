###ThreadLocal原理
ThreadLocal中利用ThreadLocalMap实现的  
ThreadLocalMap底层是数组+Entry实现的
Entry内部只有一个变量value，只存储数据

#### set原理
每次设置数据时，会获取当前线程的ThreadLocalMap对象，操作跟map类似  


#### get原理
每次获取数据时，此案获取当前当前线程的ThreadLocalMap对象，然后操作跟map的get()类似  

