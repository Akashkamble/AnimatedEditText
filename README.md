# AnimatedEditText


![animatededittext](https://user-images.githubusercontent.com/13314984/33526183-15c858e0-d863-11e7-9e01-cbf5583abbd0.gif)

## Installation
**Step 1:** Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
**Step 2:** Add the dependency


```gradle
compile 'com.github.Akashkamble:AnimatedEditText:1.0.0'
```

## How to use?

### Add following code in your layout file

```xml
<com.animatededittext.AnimateEditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
```
## How to change line color?

### Use following attribute to change line color

```xml
app:lineColor="<-COLOR->"
```

## How to change animation

### Use following attribut to change animation (default value is false)

```xml
app:animationFromStart="true"
```
