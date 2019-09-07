package org.pcl.springlongkuang.VO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Note {

    private int NRS0;
    private int NRS1;
    private int NRS2;
    private int NRS3;
    private int NRT0;
    private int NRT1;
    private int NRT2;
    private int NRT3;

    @Override
    public String toString() {
        return "Note{" +
                "NRS0=" + NRS0 +
                ", NRS1=" + NRS1 +
                ", NRS2=" + NRS2 +
                ", NRS3=" + NRS3 +
                ", NRT0=" + NRT0 +
                ", NRT1=" + NRT1 +
                ", NRT2=" + NRT2 +
                ", NRT3=" + NRT3 +
                '}';
    }
}
