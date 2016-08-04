package com.uqi.path;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shuxin on 2016/8/3.
 */
public class SvgPathToAndroidPath {
    private int svgPathLenght = 0;
    private String svgPath = null;
    private int mIndex;
    private List<Integer> cmdPositions = new ArrayList<>();
    /**
     * M x,y
     * L x,y
     * H x
     * V y
     * C x1,y1,x2,y2,x,y
     * Q x1,y1,x,y
     * S x2,y2,x,y
     * T x,y
     * */
    public Path parser(String svgPath) {
        this.svgPath = svgPath;
        svgPathLenght = svgPath.length();
        mIndex = 0;
        Path lPath = new Path();
        lPath.setFillType(Path.FillType.WINDING);
        //记录最后一个操作点
        PointF lastPoint = new PointF();
        findCommand();
        for (int i = 0; i < cmdPositions.size(); i++) {
            Integer index = cmdPositions.get(i);
            switch (svgPath.charAt(index)) {
                case 'm':
                case 'M': {
                    String ps[] = findPoints(i);
                    lastPoint.set(Float.parseFloat(ps[0]), Float.parseFloat(ps[1]));
                    lPath.moveTo(lastPoint.x, lastPoint.y);
                }
                break;
                case 'l':
                case 'L': {
                    String ps[] = findPoints(i);
                    lastPoint.set(Float.parseFloat(ps[0]), Float.parseFloat(ps[1]));
                    lPath.lineTo(lastPoint.x, lastPoint.y);
                }
                break;
                case 'h':
                case 'H': {//基于上个坐标在水平方向上划线，因此y轴不变
                    String ps[] = findPoints(i);
                    lastPoint.set(Float.parseFloat(ps[0]), lastPoint.y);
                    lPath.lineTo(lastPoint.x, lastPoint.y);
                }
                break;
                case 'v':
                case 'V': {//基于上个坐标在水平方向上划线，因此x轴不变
                    String ps[] = findPoints(i);
                    lastPoint.set(lastPoint.x, Float.parseFloat(ps[0]));
                    lPath.lineTo(lastPoint.x, lastPoint.y);
                }
                break;
                case 'c':
                case 'C': {//3次贝塞尔曲线
                    String ps[] = findPoints(i);
                    lastPoint.set(Float.parseFloat(ps[4]), Float.parseFloat(ps[5]));
                    lPath.cubicTo(Float.parseFloat(ps[0]), Float.parseFloat(ps[1]), Float.parseFloat(ps[2]), Float.parseFloat(ps[3]), Float.parseFloat(ps[4]), Float.parseFloat(ps[5]));
                }
                break;
                case 's':
                case 'S': {//一般S会跟在C或是S命令后面使用，用前一个点做起始控制点
                    String ps[] = findPoints(i);
                    lPath.cubicTo(lastPoint.x,lastPoint.y,Float.parseFloat(ps[0]), Float.parseFloat(ps[1]), Float.parseFloat(ps[2]), Float.parseFloat(ps[3]));
                    lastPoint.set(Float.parseFloat(ps[2]), Float.parseFloat(ps[3]));
                }
                break;
                case 'q':
                case 'Q': {//二次贝塞尔曲线
                    String ps[] = findPoints(i);
                    lastPoint.set(Float.parseFloat(ps[2]), Float.parseFloat(ps[3]));
                    lPath.quadTo(Float.parseFloat(ps[0]), Float.parseFloat(ps[1]), Float.parseFloat(ps[2]), Float.parseFloat(ps[3]));
                }
                break;
                case 't':
                case 'T': {//T命令会跟在Q后面使用，用Q的结束点做起始点
                    String ps[] = findPoints(i);
                    lPath.quadTo(lastPoint.x,lastPoint.y,Float.parseFloat(ps[0]), Float.parseFloat(ps[1]));
                    lastPoint.set(Float.parseFloat(ps[0]), Float.parseFloat(ps[1]));
                }
                break;
                case 'a':
                case 'A':{//画弧
                }
                break;
                case 'z':
                case 'Z': {//结束
                    lPath.close();
                }
                break;
            }
        }
        return lPath;
    }

    private String[] findPoints(int cmdIndexInPosition) {
        int cmdIndex = cmdPositions.get(cmdIndexInPosition);
        String pointString = svgPath.substring(cmdIndex + 1, cmdPositions.get(cmdIndexInPosition + 1));
        return pointString.split(",");
    }

    private void findCommand() {
        cmdPositions.clear();
        while (mIndex < svgPathLenght) {
            char c = svgPath.charAt(mIndex);
            if ('A' <= c && c <= 'Z') {
                cmdPositions.add(mIndex);
            }else if ('a' <= c && c <= 'z') {
                cmdPositions.add(mIndex);
            }
            ++mIndex;
        }
    }
}
