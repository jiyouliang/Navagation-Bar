## 底部/顶部导航控件navigation-bar

* 几分钟完成导航栏开发


### 一、截图

![](screenshot.gif)     



![](screenshot3.gif)



### 二、使用步骤

**1. 在project的build.gradle添加如下代码(如下图)**

> google()和jcenter()默认已存在

	allprojects {
	    repositories {
	        google()
	        jcenter()
	        maven { url 'https://jitpack.io' }
	    }
	}



**2. 在Module的build.gradle添加依赖**

	dependencies {
	    implementation 'com.github.jiyouliang:Navagation-Bar:v1.2'
	}


**3. 布局文件中使用NavigationBar**

	<com.jyl.navbar.NavigationBar
	        android:layout_width="match_parent"
	        android:layout_height="wrap_content"
	        android:background="@drawable/nav_divider"
	        android:paddingBottom="4dp"
	        android:paddingTop="5dp"
	        app:navNormalRes="@array/normalRes"
	        app:navSelectedRes="@array/selectedRes"
	        app:navTextColors="@array/textColors"
	        app:navTextSize="12sp"
	        app:navTitles="@array/nav_titles" />