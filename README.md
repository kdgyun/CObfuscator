
<br/>

<img src= "https://img.shields.io/badge/Java-v11.0.5-blue&?logo=Java&color=blue"/> <img src= "https://img.shields.io/badge/node.js-v16.13.0-339933&?logo=Node.js&color=339933"/> <img src= "https://img.shields.io/badge/npm-v8.1.0-CB3837&?logo=npm&color=CB3837"/>


<a href="https://hits.seeyoufarm.com"><img src="https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Fkdgyun%2FCObfuscator&count_bg=%23ED8282&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false" align="right"/></a>
<br/>

# C Obfuscator

<br/><br/> 

&nbsp;&nbsp;&nbsp; C/C++ 파일을 난독화하도록 만듭니다.

<br/><br/>  


Project
-----------
<br/>

#### CObfuscator  




> - C/C++ 를 난독화하는 프로그램이 구현되어있습니다.
> - Java11 이상 버전을 기준으로 구현됩니다.
> - Java11 API : [Java API](https://docs.oracle.com/en/java/javase/11/docs/api/index.html)
> - node.js 와 npm을 사용합니다. node.js 설치를 할 경우 대부분 자동으로 PATH를 잡아주지만, 간혹 안되는 경우도 있으니 환경변수는 필히 확인하고 설정해주셔야 합니다. 
> - UNIX 계열 OS에 최적화되어있습니다. (추후 OS 확장 예정)


</br></br>
installing node.js : (https://nodejs.org/)


<br/><br/>

-----------------




## Usage (COMMON OPTION)

<br/>

**CLI common option**
```css
java conf/util/runner [input file path] [output file path] <option>
```
<br/><br/>

**conf/util/runner** : The execution location of the main file(.class). <br/>
**input file path** : Relative path or absolute path of the file to be converted from the current directory location.<br/>
**output file path** : Relative path or absolute path of the resulting file from the current directory location. (make new file in run)<br/><br/>





**\<option>**<br/>
For printing to colsole, set the --p or --P flag
```Ruby
java conf/util/runner workspace/hello/main.c  workspace/hello/conv_main.c --P
```
<br/>

For  getting output result to file, set the --f or --F flag <br/>
```Ruby
--f / --F : get output result to file
```
<br/><br/>



If the input file path is not specified, it will run as a built-in test file.
```Ruby
#default input
java conf/util/runner    
#(equals : java CObfuscator/src/cobf/test/inputFile/main.c --p)

java conf/util/runner --f    
#(equals : java CObfuscator/src/cobf/test/inputFile/main.c CObfuscator/src/cobf/test/outputFile/Obfmain.c --F)
```

If the option is --F and output file path is not specified, it will created file with a name that combines "Obf" and the input file name in a directory such as an input file.
```Ruby
#default input
java conf/util/runner ../work/hello/world.c --p 
(equals : java ../work/hello/world.c --p )

java conf/util/runner ../work/hello/world.c --f
(equals : java ../work/hello/world.c ../work/hello/Obfworld.c --F )
```





-----------------



## How to Use 1 (using CLI)
<br/>

**1. Download** <br /> <br /> 
```Ruby
git clone git@github.com:kdgyun/CObfuscator.git
```

<br /><br />

**2. Build and Compile** 
<br /> <br /> 

- **Step1 : change directory** 
```Ruby
cd CObfuscator
```

<br />

- **Step2 : compile** <br /> 
```Ruby
# if you want to compile the all java source files in current directory
javac src/**/*.java
cd src
```
or

```Ruby
# if you want to compile the all java source files in other directory(ex CObfuscator/bin/)
javac -d bin/ src/**/*.java
cp -r src/cobf/run.sh src/cobf/js src/cobf/test bin/cobf/
cd bin
```

<br />

**3. Run** 
```
java conf/util/runner [input file path] [output file path] <option>
```




<br /><br /><br /><br />
## How to Use 2 (download .jar and using CLI)
<br/>

**1. Download** <br /> <br /> 
download jar : (https://github.com/kdgyun/CObfuscator/releases/download/v1.0.3/CObfuscator.jar)

<br /><br />

**2. Extracting jar** 
<br /> <br /> 

- **Step1 : change directory** 
```Ruby
cd CObfuscator
```

<br />

- **Step2 : Extracting** <br /> 
```Ruby
# if you want to compile the all java source files in current directory
jar xvf CObfuscator.jar
```

<br />

**3. compile**

```Ruby
javac cobf/**/*.java
```

<br />

**4. Run** 
```
java conf/util/runner [input file path] [output file path] <option>
```




<br /><br /><br /><br />
## How to Use 3 (download .jar and using Eclipse)
<br/>

**1. Download** <br /> <br /> 
download jar : (https://github.com/kdgyun/CObfuscator/releases/download/v1.0.3/CObfuscator.jar)

<br /><br />

**2. import jar** 
<br /> <br /> 

 1. Right click on your project -> Buid Path -> click on '**new Source Folder...**' -> input folder name(ex. Folder name : lib) and finish 
 
 2. Right click on made Source Folder ->  click on '**Import..**' -> click on '**Archive File**' in General -> click on Browse and search for downloaded CObfuscator.jar and finish
 
 3. input folder name(ex. Folder name : lib) and finish 



<br /><br />

**3. using it!**

```Java
# ex1)
import java.io.IOException;
import cobf.util.runner;

public class test {
	public static void main(String[] args) {
		
		try {
			runner.main(new String[] {"/workspace/dir1/hello_world.cpp", "--f"});
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

```

<br />


```Ruby
# ex1)
import cobf.util.cObfuscator;

public class test {
	public static void main(String[] args) {
		// param : [input file path], [output file path], [console print : true / get file : false]
		new cObfuscator().run("/test/inputFile/main.c","/test/outputFile/Obfmain.c", true);

	}
}


```




