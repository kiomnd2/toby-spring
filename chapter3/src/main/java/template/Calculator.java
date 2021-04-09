package template;

import javax.sound.sampled.Line;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
    public int calcSum(String path) throws IOException {
        /*BufferedReaderCallback sumCallback =
                new BufferedReaderCallback() {
                    @Override
                    public Integer doSomethingWithReader(BufferedReader br) throws IOException {
                        int sum = 0;
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            sum += Integer.parseInt(line);
                        }
                        return sum;
                    }
                };
        return fileReadTemplate(path, sumCallback);*/
        LineCallback sumCallback =
                new LineCallback<Integer>() {
                    @Override
                    public Integer doSomethingWithLine(String line, Integer value) {
                        return value + Integer.parseInt(line);
                    }
                };
        return  lineReadTemplate(path, sumCallback, 0);
    }


    public Integer calcMultiply(String numFilepath) throws IOException {
        /*BufferedReaderCallback multiplyCallback =
                new BufferedReaderCallback() {
                    @Override
                    public Integer doSomethingWithReader(BufferedReader br) throws IOException {
                        Integer multiply = 1;
                        String line = null;
                        while((line = br.readLine()) != null) {
                            multiply *= Integer.parseInt(line);
                        }
                        return multiply;
                    }
                };
        return fileReadTemplate(numFilepath, multiplyCallback);*/
        LineCallback sumCallback =
                new LineCallback<Integer>() {
                    @Override
                    public Integer doSomethingWithLine(String line, Integer value) {
                        return value * Integer.parseInt(line);
                    }
                };
        return  lineReadTemplate(numFilepath, sumCallback, 0);
    }

    public Integer fileReadTemplate(String filePath, BufferedReaderCallback callback) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            int ret = callback.doSomethingWithReader(br);
            return ret;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                }
                catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public <T> T lineReadTemplate(String filepath, LineCallback<T> lineCallback, T initVal) throws IOException {
        T res = initVal;
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                res = lineCallback.doSomethingWithLine(line, res);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return res;
    }

}
