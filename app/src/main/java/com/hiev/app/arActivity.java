package com.hiev.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;

import java.util.Objects;

public class arActivity extends AppCompatActivity {
    private ArFragment arCam; //object of ArFragment Class
    private int clickNo = 0; //helps to render the 3d model only once when we tap the screen
    public static boolean checkSystemSupport(Activity activity) {

        //checking whether the API version of the running Android >= 24 that means Android Nougat 7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            String openGlVersion = ((ActivityManager) Objects.requireNonNull(activity.getSystemService(Context.ACTIVITY_SERVICE))).getDeviceConfigurationInfo().getGlEsVersion();

            //checking whether the OpenGL version >= 3.0
            if (Double.parseDouble(openGlVersion) >= 3.0) {
                return true;
            } else {
                Toast.makeText(activity, "App needs OpenGl Version 3.0 or later", Toast.LENGTH_SHORT).show();
                activity.finish();
                return false;
            }
        } else {
            Toast.makeText(activity, "App does not support required Build Version", Toast.LENGTH_SHORT).show();
            activity.finish();
            return false;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);

        if (checkSystemSupport(this)) {

            arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);
            //ArFragment is linked up with its respective id used in the activity_main.xml

            arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                clickNo++;
                //the 3d model comes to the scene only when clickNo is one that means once
                if (clickNo == 1) {
                    Anchor anchor = hitResult.createAnchor();
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    anchorNode.setParent(arCam.getArSceneView().getScene());
                    //render maps
                    ModelRenderable.builder()
                            .setSource(this, R.raw.anchor)
                            .setIsFilamentGltf(true)
                            .build()
                            .thenAccept(modelRenderable -> addMaps(anchorNode, modelRenderable))
                            .exceptionally(throwable -> {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setMessage("Somthing is not right" + throwable.getMessage()).show();
                                return null;
                            });


                    arCam.getArSceneView().getScene().addChild(anchorNode);
                    addJudul(R.raw.tkebon,anchorNode,0.048f,1.3f,0.19f);
                    addGambar(R.raw.kebon,anchorNode,0.048f,1.5f,0.19f);
                }

            });

        } else {

            return;

        }
    }

    private void addMaps(AnchorNode anchorNode, ModelRenderable modelRenderable) {
        Node modelMaps = new Node();
        modelMaps.setParent(anchorNode);
        modelMaps.setRenderable(modelRenderable);
        modelMaps.setLocalScale(new Vector3(0.001f, 0.001f, 0.001f));
    }

    private void addBtnInfo(Integer modelSource, AnchorNode anchorNode, float xPos, float yPos, float zPos) {
        ModelRenderable.builder()
                .setSource(this, modelSource)
                .setIsFilamentGltf(true)
                .build()
                .thenAccept(modelRenderable -> {
                    Node modelBtnInfo = new Node();
                    modelBtnInfo.setLocalScale(new Vector3(0.009f, 0.009f, 0.009f));
                    modelBtnInfo.setLocalPosition(new Vector3(xPos,yPos,zPos));
                    modelBtnInfo.setLocalRotation(Quaternion.multiply(Quaternion.axisAngle(Vector3.right(), 90f),Quaternion.axisAngle(Vector3.up(), 90f)));
                    modelBtnInfo.setRenderable(modelRenderable);
                    anchorNode.addChild(modelBtnInfo);
                    modelBtnInfo.setOnTapListener((positon,event)->{
                        //move to other activity
                        Intent intent = new Intent(this, detailActivity.class);
                        startActivity(intent);
                        clickNo = 0;
                    });
                });
    }
    private void addJudul(Integer modelSource, AnchorNode anchorNode, float xPos, float yPos, float zPos){
        ModelRenderable.builder()
                .setSource(this, modelSource)
                .setIsFilamentGltf(true)
                .build()
                .thenAccept(modelRenderable -> {
                    Node modelJudul = new Node();
                    modelJudul.setLocalScale(new Vector3(0.01f, 0.01f, 0.01f));
                    modelJudul.setLocalPosition(new Vector3(xPos,yPos,zPos));
                    modelJudul.setLocalRotation(Quaternion.multiply(Quaternion.axisAngle(Vector3.right(), 180f),Quaternion.axisAngle(Vector3.up(), 90f)));
                    modelJudul.setRenderable(modelRenderable);
                    anchorNode.addChild(modelJudul);
                });
    }
    private void addGambar(Integer modelSource, AnchorNode anchorNode, float xPos, float yPos, float zPos){
        ModelRenderable.builder()
                .setSource(this, modelSource)
                .setIsFilamentGltf(true)
                .build()
                .thenAccept(modelRenderable -> {
                    Node modelJudul = new Node();
                    modelJudul.setLocalScale(new Vector3(0.001f, 0.001f, 0.001f));
                    modelJudul.setLocalPosition(new Vector3(xPos,yPos,zPos));
                    modelJudul.setLocalRotation(Quaternion.multiply(Quaternion.axisAngle(Vector3.right(), 90f),Quaternion.axisAngle(Vector3.up(), 90f)));
                    modelJudul.setRenderable(modelRenderable);
                    anchorNode.addChild(modelJudul);
                });
    }

}
