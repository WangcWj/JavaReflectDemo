package com.wang.javareflect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Class<XiaoMingBean> mClass = XiaoMingBean.class;
        getFilesAndMethods(mClass);
        invokMethod(mClass);
        /*
        * 通过Java的反射我们可以反向的得知一个类里面的属性,方法,构造函数.
        * 然后可以构造方法得到构造方法需要的参数进而创建对象,在访问除public修饰的方法或者属性的时候
        * 记得setAccessible(true)这样才能保证访问的到.
        *
        * */
    }

    private void invokMethod(Class<XiaoMingBean> mClass) {
    /*  这些得到的是类的公共构造方法
        Constructor<?>[] constructors = mClass.getConstructors();
        mClass.getConstructor("参数类型")
        这些是获取制定类的所有构造方法
        mClass.getDeclaredConstructors()
        mClass.getDeclaredConstructor()*/
        try {

            /*
            获取类的对象实例:
            1.mClass.newInstance();
            如果没有默认的构造器会调用失败
            2.通过得到构造器的对象来调用构造器的newInstance方法来
            */
            Constructor<?>[] declaredConstructors = mClass.getDeclaredConstructors();
            for (int i = 0; i <declaredConstructors.length ; i++) {
                Constructor<?> declaredConstructor = declaredConstructors[i];
                Class<?>[] parameterTypes = declaredConstructor.getParameterTypes();
                for (int j = 0; j < parameterTypes.length; j++) {
                    Class<?> parameterType = parameterTypes[i];
                    Log.e("CHAO", "构造方法的每个参数 ->"+parameterType.getName());
                }
            }

            Constructor<?> declaredConstructor = declaredConstructors[0];
            declaredConstructor.setAccessible(true);   //访问私有属性的时候要记得设置.
            XiaoMingBean o = (XiaoMingBean) declaredConstructor.newInstance("喜欢", "不会");
             Log.e("CHAO", "instance ->"+o);
            if(o!= null){
                o.init("喜欢啊!");
            }
            Method privateMethod = mClass.getDeclaredMethod("init", String.class);
            if(privateMethod != null) {
                //设置可以访问,但并不是改变他的访问权限
                privateMethod.setAccessible(true);
                privateMethod.invoke(o, "会啊");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void getFilesAndMethods(Class<XiaoMingBean> mClass) {
        Log.e("CHAO", "获取类名称:"+mClass.getName());
        /*getFilds() 方法获取的是public属性,包括父类的定义的.
        *getDeclaredFilds 返回的是制定类中所有定义的属性,不包括父类的.
        *
        * */
        Field[] fields = mClass.getFields(); //获取public权限属性
        Field[] declaredFields = mClass.getDeclaredFields();//获取所有的权限的属性
        for (Field field: declaredFields) {
            int modifiers = field.getModifiers();
            //  Log.e("CHAO", "获取属性权限:"+ Modifier.toString(modifiers));
            //Log.e("CHAO","变量类型:"+field.getType().getName()+"变量名称:"+field.getName());
        }
        Method[] methods = mClass.getMethods();
        Method[] declaredMethods = mClass.getDeclaredMethods();
        for (Method method: declaredMethods) {
            int modifiers = method.getModifiers();
            //  Log.e("CHAO", "获取方法权限:"+ Modifier.toString(modifiers));
            String name = method.getName();
        //    Log.e("CHAO", "方法名称:"+name);
            Type genericReturnType = method.getGenericReturnType();
            // Log.e("CHAO", "genericReturnType: "+genericReturnType);
            Class<?> returnType = method.getReturnType(); //方法的返回值类型
            //  Log.e("CHAO", "returnType:  "+returnType);
            Class<?>[] parameterTypes = method.getParameterTypes();
            // Log.e("CHAO", "parameterTypes:  "+parameterTypes);
            TypeVariable<Method>[] typeParameters = method.getTypeParameters();
            for (int i = 0; i < typeParameters.length; i++) {
                TypeVariable<Method> typeParameter = typeParameters[0];
                String name1 = typeParameter.getName();
                //Log.e("CHAO", "typeParameter.getName ->"+name1);

            }
        }
    }
}
