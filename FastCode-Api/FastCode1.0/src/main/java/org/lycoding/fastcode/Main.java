package org.lycoding.fastcode;

import javax.swing.*;
import java.io.File;

/**
 * @Author: lycoding
 * @DateTime: 2024/11/10 22:09
 **/
public class Main {
    public static void main(String[] args) {
        // 创建一个JFileChooser实例
        JFileChooser fileChooser = new JFileChooser();

        // 设置选择模式为文件
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // 显示文件选择对话框
        int returnValue = fileChooser.showOpenDialog(null);

        // 判断用户的选择
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            // 获取用户选择的文件
            File selectedFile = fileChooser.getSelectedFile();
            // 打印文件路径
            System.out.println("选择的文件: " + selectedFile.getAbsolutePath());
        } else {
            System.out.println("没有选择文件");
        }
    }
}
