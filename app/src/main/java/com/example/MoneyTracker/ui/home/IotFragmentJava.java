package com.example.jizhangbao.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jizhangbao.R;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class IotFragmentJava extends Fragment {


    private MqttClient client;
    private MqttConnectOptions options;
    private String mqttURL = "tcp://mqtt.flyweb.cn:1883";
    private String userID = "139311968";
    private String userPwd = "123456";
    private String sub_topic_connected = "testtopic/connected";
    private String sub_topic_relay = "testtopic/relay";
    private String pub_topic_isconnected = "testtopic/isconnected";

    private Handler handler;
    private boolean isConnected = false;
    private Button btn_sync_on;
    private Button btn_sync_off;


    @SuppressLint("HandlerLeak")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_iot_java, container, false);

        Button btn_sta = view.findViewById(R.id.device_status_button); // 连接MQTT服务按钮
        btn_sync_on = view.findViewById(R.id.sync_ON_button); // 发送消息开启设备按钮
        btn_sync_off = view.findViewById(R.id.sync_OFF_button); // 发送消息关闭设备按钮

        handler = new Handler(Looper.getMainLooper());

        // 初始状态下，功能按钮不可用
        btn_sync_on.setEnabled(false);
        btn_sync_off.setEnabled(false);

        // 连接按钮点击事件
        btn_sta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (client == null || !client.isConnected()) {
                    connectToMqttBroker();
                }

                if (client != null && client.isConnected()) {
                    publishMessage(pub_topic_isconnected, "1");
                    // 禁用按钮，直到连接验证成功
                    btn_sync_on.setEnabled(false);
                    btn_sync_off.setEnabled(false);

                    // 设置连接验证超时
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (!isConnected) {
                                Toast.makeText(getContext(), "连接失败", Toast.LENGTH_LONG).show();
                                btn_sync_on.setEnabled(false);
                                btn_sync_off.setEnabled(false);
                            }
                        }
                    }, 5000); // 5秒超时
                } else {
                    Toast.makeText(getContext(), "MQTT客户端未连接", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_sync_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publishMessage(sub_topic_relay, "Device ON");
                Toast.makeText(getContext(), "已开启", Toast.LENGTH_SHORT).show();
            }
        });

        btn_sync_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publishMessage(sub_topic_relay, "Device OFF");
                Toast.makeText(getContext(), "已关闭", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void connectToMqttBroker() {
        try {
            client = new MqttClient(mqttURL, MqttClient.generateClientId(), null);
            options = new MqttConnectOptions();
            options.setUserName(userID);
            options.setPassword(userPwd.toCharArray());
            options.setCleanSession(true);

            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    handler.post(() -> {
                        isConnected = false;
                        btn_sync_on.setEnabled(false);
                        btn_sync_off.setEnabled(false);
                    });
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    handler.post(() -> {
                        String payload = new String(message.getPayload());
                        System.out.println("收到消息: " + payload);

                        // 处理设备连接验证消息
                        if (topic.equals(sub_topic_connected) && payload.equals("31")) {
                            isConnected = true;
                            btn_sync_on.setEnabled(true);
                            btn_sync_off.setEnabled(true);
                            Toast.makeText(getContext(), "连接成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // 不需要在消息成功发送后弹出toast
                }
            });

            // 使用同步连接方法
            client.connect(options);
            try {
                client.subscribe(sub_topic_connected);
                client.subscribe(sub_topic_relay);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        } catch (MqttException e) {
            e.printStackTrace();
            handler.post(() -> {
                isConnected = false;
                Toast.makeText(getContext(), "MQTT连接失败: " + e.getMessage(), Toast.LENGTH_LONG).show();
                btn_sync_on.setEnabled(false);
                btn_sync_off.setEnabled(false);
            });
        }
    }

    private void publishMessage(String topic, String payload) {
        if (client != null && client.isConnected()) {
            try {
                MqttMessage message = new MqttMessage(payload.getBytes());
                client.publish(topic, message);
                System.out.println("消息发布到 " + topic + ": " + payload);
            } catch (MqttException e) {
                e.printStackTrace();
                handler.post(() -> Toast.makeText(getContext(), "消息发布失败: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }
        } else {
            handler.post(() -> Toast.makeText(getContext(), "MQTT客户端未连接", Toast.LENGTH_SHORT).show());
        }
    }
}