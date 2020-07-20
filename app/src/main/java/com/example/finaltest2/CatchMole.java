package com.example.finaltest2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import java.util.Random;
public class CatchMole extends View implements Runnable{

    //해상도: 1080x1920
    int oppor=3;
    int grade=0;
    //    private Point mPoint=null;
    private Rect mRect;
    boolean isRun=true;

    public CatchMole(Context context) {
        super(context);

        Thread thread=new Thread(this);
        thread.start();


        mRect=new Rect(0,0,100,100);
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p=new Paint();
        p.setTextSize(50);
        p.setColor(Color.BLUE);

        canvas.drawText("기회: "+oppor,0,50,p);
        canvas.drawText("점수: "+grade,0,120,p);

        //하나의 사각형 영역을 그리기
        p.setColor(Color.RED);
        canvas.drawRect(mRect,p);



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        int cx=(int)event.getX();
        int cy=(int)event.getY();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(mRect.contains(cx,cy)){

                    grade+=1;}
                else{
                    oppor-=1;
                    if(oppor==0){
                        isRun=false;
                    }
                    invalidate();
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        return false;
    }
    @Override
    public void run() {

        while (isRun) {
            Random random = new Random();


            int k = random.nextInt(1000);
            int k1 = random.nextInt(1000);

            mRect.offsetTo(k,k1);
//            mRect.offset(k,k1);

            invalidate();

            Message message=new Message();
            message.what=1;


            try {

                Thread.sleep(1500);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
