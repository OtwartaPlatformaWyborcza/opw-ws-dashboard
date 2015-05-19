/*
 * The MIT License
 *
 * Copyright 2015 Adam Kowalewski.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package pl.otwartapw.opw.ws.dashboard.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Adam Kowalewski
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WynikDto {

    private Short uprawnionych;
    private Short glosujacych;
    private Short kartWaznych;
    private Short glosowNieWaznych;
    private Short glosowWaznych;

    private Short k1;
    private Short k2;
    private Short k3;
    private Short k4;
    private Short k5;
    private Short k6;
    private Short k7;
    private Short k8;
    private Short k9;
    private Short k10;
    private Short k11;

    private String timestampCreated;
    private int ratedPositiv;
    private int ratedNegativ;

    private List<LinkDto> linkList;

    public WynikDto() {
        linkList = new ArrayList<>();
    }

    public WynikDto(Short uprawnionych, Short glosujacych, Short kartWaznych, Short glosowNieWaznych, Short glosowWaznych, Short k1, Short k2, Short k3, Short k4, Short k5, Short k6, Short k7, Short k8, Short k9, Short k10, Short k11) {
        this.uprawnionych = uprawnionych;
        this.glosujacych = glosujacych;
        this.kartWaznych = kartWaznych;
        this.glosowNieWaznych = glosowNieWaznych;
        this.glosowWaznych = glosowWaznych;
        this.k1 = k1;
        this.k2 = k2;
        this.k3 = k3;
        this.k4 = k4;
        this.k5 = k5;
        this.k6 = k6;
        this.k7 = k7;
        this.k8 = k8;
        this.k9 = k9;
        this.k10 = k10;
        this.k11 = k11;
        linkList = new ArrayList<>();
    }

    public Short getUprawnionych() {
        return uprawnionych;
    }

    public void setUprawnionych(Short uprawnionych) {
        this.uprawnionych = uprawnionych;
    }

    public Short getGlosujacych() {
        return glosujacych;
    }

    public void setGlosujacych(Short glosujacych) {
        this.glosujacych = glosujacych;
    }

    public Short getKartWaznych() {
        return kartWaznych;
    }

    public void setKartWaznych(Short kartWaznych) {
        this.kartWaznych = kartWaznych;
    }

    public Short getGlosowNieWaznych() {
        return glosowNieWaznych;
    }

    public void setGlosowNieWaznych(Short glosowNieWaznych) {
        this.glosowNieWaznych = glosowNieWaznych;
    }

    public Short getGlosowWaznych() {
        return glosowWaznych;
    }

    public void setGlosowWaznych(Short glosowWaznych) {
        this.glosowWaznych = glosowWaznych;
    }

    public Short getK1() {
        return k1;
    }

    public void setK1(Short k1) {
        this.k1 = k1;
    }

    public Short getK2() {
        return k2;
    }

    public void setK2(Short k2) {
        this.k2 = k2;
    }

    public Short getK3() {
        return k3;
    }

    public void setK3(Short k3) {
        this.k3 = k3;
    }

    public Short getK4() {
        return k4;
    }

    public void setK4(Short k4) {
        this.k4 = k4;
    }

    public Short getK5() {
        return k5;
    }

    public void setK5(Short k5) {
        this.k5 = k5;
    }

    public Short getK6() {
        return k6;
    }

    public void setK6(Short k6) {
        this.k6 = k6;
    }

    public Short getK7() {
        return k7;
    }

    public void setK7(Short k7) {
        this.k7 = k7;
    }

    public Short getK8() {
        return k8;
    }

    public void setK8(Short k8) {
        this.k8 = k8;
    }

    public Short getK9() {
        return k9;
    }

    public void setK9(Short k9) {
        this.k9 = k9;
    }

    public Short getK10() {
        return k10;
    }

    public void setK10(Short k10) {
        this.k10 = k10;
    }

    public Short getK11() {
        return k11;
    }

    public void setK11(Short k11) {
        this.k11 = k11;
    }

    public String getTimestampCreated() {
        return timestampCreated;
    }

    public void setTimestampCreated(String timestampCreated) {
        this.timestampCreated = timestampCreated;
    }

    public int getRatedPositiv() {
        return ratedPositiv;
    }

    public void setRatedPositiv(int ratedPositiv) {
        this.ratedPositiv = ratedPositiv;
    }

    public int getRatedNegativ() {
        return ratedNegativ;
    }

    public void setRatedNegativ(int ratedNegativ) {
        this.ratedNegativ = ratedNegativ;
    }

    public List<LinkDto> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<LinkDto> linkList) {
        this.linkList = linkList;
    }

    @Override
    public String toString() {
        return "WynikDto{" + "uprawnionych=" + uprawnionych + ", glosujacych=" + glosujacych + ", kartWaznych=" + kartWaznych + ", glosowNieWaznych=" + glosowNieWaznych + ", glosowWaznych=" + glosowWaznych + ", k1=" + k1 + ", k2=" + k2 + ", k3=" + k3 + ", k4=" + k4 + ", k5=" + k5 + ", k6=" + k6 + ", k7=" + k7 + ", k8=" + k8 + ", k9=" + k9 + ", k10=" + k10 + ", k11=" + k11 + ", timestampCreated=" + timestampCreated + ", ratedPositiv=" + ratedPositiv + ", ratedNegativ=" + ratedNegativ + '}';
    }

}
