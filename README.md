# ChinaMapView

------

**一个用Path 绘制的中国省份地图**<br><br><br>
![演示](https://github.com/xchengx/ChinaMap/blob/844caac9c943ff30b73ae3666530593678098294/screen/sc.gif)

---
##使用
* 布局文件中
```Xml
<com.uqi.path.ChinaMapView
        android:id="@+id/vp"
        android:background="#FFFF6F"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```
* Java中<br>

###1.监听选中的省份
```Java
ChinaMapView lView = (ChinaMapView)findViewById(R.id.vp);
        lView.setOnProvinceSelectedListener(new ChinaMapView.OnProvinceSelectedListener() {
            @Override
            public void onprovinceSelected(ChinaMapView.Area pArea) {
                Toast.makeText(MainActivity.this,"您选择了-->"+pArea.name(),Toast.LENGTH_SHORT).show();
            }
        });
```
###2.高亮某个省份
```Java
ChinaMapView lView = (ChinaMapView)findViewById(R.id.vp);
lView.setPaintColor(ChinaMapView.Area.SiChuan, Color.rgb(0x5c,0xad,0xad),true);
```
###3.默认选中某个省份
```Java
ChinaMapView lView = (ChinaMapView)findViewById(R.id.vp);
lView.selectAProvince(ChinaMapView.Area.XinJiang);
```
###4.移动
```Java
ChinaMapView lView = (ChinaMapView)findViewById(R.id.vp);
lView.up();//上
lView.down();//下
lView.left();//左
lView.right();//右
lView.restPosition();//复位
```
###4.放大缩小
```Java
ChinaMapView lView = (ChinaMapView)findViewById(R.id.vp);
lView.zoomIn();//放大
lView.zoomOut();//缩小
lView.restScale();//原始大小
```

> Tips:
> * 1.View会使用的时候重新计算大小，等比缩放View，用宽度来计算高度,建议layout_width使用match_parent
> * 2.View中没有对移动做边界处理
> * 3.View中缩放没有指定缩放坐标，因此在缩放后会感觉跑偏
