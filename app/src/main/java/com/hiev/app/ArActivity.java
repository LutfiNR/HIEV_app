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
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import java.util.Objects;
public class ArActivity extends AppCompatActivity {
    private ArFragment arCam;
    private int clickNo = 0;
    public static boolean checkSystemSupport(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String openGlVersion = ((ActivityManager) Objects.requireNonNull(activity.getSystemService(Context.ACTIVITY_SERVICE))).getDeviceConfigurationInfo().getGlEsVersion();
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
            assert arCam != null;
            arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                clickNo++;
                if (clickNo == 1) {
                    Anchor anchor = hitResult.createAnchor();
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    anchorNode.setParent(arCam.getArSceneView().getScene());
                    ModelRenderable.builder()
                            .setSource(this, R.raw.anchor)
                            .setIsFilamentGltf(true)
                            .build()
                            .thenAccept(modelRenderable -> createAnchor(anchorNode, modelRenderable))
                            .exceptionally(throwable -> {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setMessage("Somthing is not right" + throwable.getMessage()).show();
                                return null;
                            });
                    arCam.getArSceneView().getScene().addChild(anchorNode);
                    addModel(R.raw.bapak,anchorNode,0.005f,"adolf");
                    addModel(R.raw.kebon,anchorNode,0.005f,"pertanian");
                    addModel(R.raw.tokoh,anchorNode,0.005f,"tokoh");
                    addModel(R.raw.pemukiman,anchorNode,0.005f,"persinggahan");
                    addModel(R.raw.pertanian,anchorNode,0.005f,"pertanian");
                    addModel(R.raw.sekolah,anchorNode,0.005f,"sekolah");
                }
            });
        }
    }
    private void createAnchor(AnchorNode anchorNode, ModelRenderable modelRenderable){
        Node modelMaps = new Node();
        modelMaps.setParent(anchorNode);
        modelMaps.setRenderable(modelRenderable);
        modelMaps.setLocalScale(new Vector3(0.001f, 0.001f, 0.001f));
    }
    private void addModel(Integer modelSource, AnchorNode anchorNode,float scale,String id){
        ModelRenderable.builder()
                .setSource(this, modelSource)
                .setIsFilamentGltf(true)
                .build()
                .thenAccept(modelRenderable -> {
                    Node model = new Node();
                    model.setLocalScale(new Vector3(scale, scale, scale));
                    model.setRenderable(modelRenderable);
                    anchorNode.addChild(model);
                    model.setOnTapListener((position, event) -> {
                        Intent intent = new Intent(this, DetailActivity.class);
                        intent.putExtra("passid", id);
                        startActivity(intent);
                    });
                });
    }

}
