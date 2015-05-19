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
package pl.otwartapw.opw.ws.dashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.*;
import pl.otwartapw.opw.ws.dashboard.bean.KandydatBean;
import pl.otwartapw.opw.ws.dashboard.bean.LinkBean;
import pl.otwartapw.opw.ws.dashboard.bean.WynikBean;
import pl.otwartapw.opw.ws.dashboard.dto.DashboardDto;
import pl.otwartapw.opw.ws.dashboard.dto.KandydatDto;
import pl.otwartapw.opw.ws.dashboard.dto.LinkDto;
import pl.otwartapw.opw.ws.dashboard.dto.WynikDto;
import pl.otwartapw.opw.ws.dashboard.entity.OpwKandydat;
import pl.otwartapw.opw.ws.dashboard.entity.OpwLink;
import pl.otwartapw.opw.ws.dashboard.entity.OpwWynik;

/**
 * Provides business logic for webservice around Wynik.
 *
 * @author Adam Kowalewski
 */
@Stateless
public class DashboardServiceEjb implements Serializable {

    private final static Logger logger = LoggerFactory.getLogger(DashboardServiceEjb.class);

    private final static int OBWODOWA_ALL = 27816;
    private final static int FREKWENCJA_ALL = 30229501;

    @EJB
    KandydatBean kandydatBean;
    @EJB
    WynikBean wynikBean;
    @EJB
    LinkBean linkBean;

    public DashboardServiceEjb() {
    }

    public Response loadWynikSingle(int wynikId) {

        OpwWynik wynik = wynikBean.find(wynikId);

        WynikDto result = new WynikDto(
                wynik.getLUprawnionych(), wynik.getLKartWydanych(),
                wynik.getLKartWaznych(),
                wynik.getLGlosowNiewaznych(), wynik.getLGlosowWaznych(),
                wynik.getK1(), wynik.getK2(), wynik.getK3(), wynik.getK4(),
                wynik.getK5(), wynik.getK6(), wynik.getK7(), wynik.getK8(),
                wynik.getK9(), wynik.getK10(), wynik.getK11());

        List<LinkDto> linkList = new ArrayList<>();
        for (OpwLink link : linkBean.findAll(wynik, true)) {
            if (link.getActive()) {
                LinkDto l = new LinkDto(link.getId(), link.getLabel(), link.getUrl(), link.getComment(), String.valueOf(link.getDateCreated().getTime()));
                linkList.add(l);
            }
        }

        result.setLinkList(linkList);

        result.setRatedPositiv(wynik.getRatedPositiv());
        result.setRatedNegativ(wynik.getRatedNegativ());
        result.setTimestampCreated(String.valueOf(wynik.getDateCreated().getTime()));
        return Response.ok().entity(result).build();

    }

    public Response wynik() {
        try {
            int k1 = 0, k2 = 0, k3 = 0, k4 = 0, k5 = 0, k6 = 0, k7 = 0, k8 = 0, k9 = 0, k10 = 0, k11 = 0,
                    votersValid = 0;

            List<OpwWynik> currentElectionResults = wynikBean.fetchCurrentElectionResults();
            for (OpwWynik wynik : currentElectionResults) {
                k1 += wynik.getK1();
                k2 += wynik.getK2();
                k3 += wynik.getK3();
                k4 += wynik.getK4();
                k5 += wynik.getK5();
                k6 += wynik.getK6();
                k7 += wynik.getK7();
                k8 += wynik.getK8();
                k9 += wynik.getK9();
                k10 += wynik.getK10();
                k11 += wynik.getK11();
                votersValid += wynik.getLKartWydanych();
            }

            List<OpwKandydat> kandydatList = kandydatBean.findAll();

            DashboardDto dashboard = new DashboardDto();
            for (OpwKandydat opwKandydat : kandydatList) {
                dashboard.getKandydatList().add(new KandydatDto(
                        opwKandydat.getPkwId(), opwKandydat.getFirstname(), opwKandydat.getLastname(), 0)
                );
            }

            dashboard.setExportDate(String.valueOf(new Date().getTime()));
            dashboard.setObwodowa(currentElectionResults.size());
            dashboard.setObwodowaAll(OBWODOWA_ALL);
            dashboard.setFrekwencja(votersValid);
            dashboard.setFrekwencjaAll(FREKWENCJA_ALL);
            dashboard.getKandydatList().get(0).setGlosow(k1);
            dashboard.getKandydatList().get(1).setGlosow(k2);
            dashboard.getKandydatList().get(2).setGlosow(k3);
            dashboard.getKandydatList().get(3).setGlosow(k4);
            dashboard.getKandydatList().get(4).setGlosow(k5);
            dashboard.getKandydatList().get(5).setGlosow(k6);
            dashboard.getKandydatList().get(6).setGlosow(k7);
            dashboard.getKandydatList().get(7).setGlosow(k8);
            dashboard.getKandydatList().get(8).setGlosow(k9);
            dashboard.getKandydatList().get(9).setGlosow(k10);
            dashboard.getKandydatList().get(10).setGlosow(k11);
            //TODO Dodac komisje okregowe
            return Response.ok().entity(dashboard).build();
        } catch (Exception e) {
            logger.error("Ex ", e);
            return Response.status(BAD_REQUEST).build();
        }
    }

    public List<OpwKandydat> kandydatFindAll() {
        return kandydatBean.findAll();
    }

}
