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

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.otwartapw.opw.ws.dashboard.bean.ConfigBean;
import pl.otwartapw.opw.ws.dashboard.dto.DashboardDto;
import pl.otwartapw.opw.ws.dashboard.dto.KandydatDto;
import pl.otwartapw.opw.ws.dashboard.entity.OpwKandydat;

/**
 * Represents wynik perspective. Main service for all OPW dashboard
 * applications.
 *
 * @author Adam Kowalewski
 */
@Path("/dashboard")
@RequestScoped
public class DashboardService implements Serializable{

    private final static Logger logger = LoggerFactory.getLogger(DashboardService.class);
    static final String OPW_HEADER_API_TOKEN = "X-OPW-API-token";

    @EJB
    DashboardServiceEjb dashboardEjb;
    @EJB
    ConfigBean configBean;

    @GET
    @Path("/{wynikId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response loadWynikSingle(
            @NotNull @PathParam("wynikId") int wynikId) {

        logger.trace("load wynik {}", wynikId);

        return dashboardEjb.loadWynikSingle(wynikId);

    }

    @GET
    @Path("/complete")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response wynik(@NotNull @HeaderParam(OPW_HEADER_API_TOKEN) String apiToken) {

        // TODO refactoring konieczny 
        // aktualne liczby dostepne na http://prezydent2015.pkw.gov.pl/
        if (Boolean.valueOf(configBean.readConfigValue(OpwWsConfigStatic.CFG_KEY_CISZA_WYBORCZA))) {
            DashboardDto result = new DashboardDto(String.valueOf(new Date().getTime()), 27817, 0, 30768394, 0);

            List<OpwKandydat> kandydatList = dashboardEjb.kandydatFindAll();

            for (OpwKandydat kandydat : kandydatList) {
                KandydatDto k = new KandydatDto(kandydat.getPkwId(), kandydat.getFirstname(), kandydat.getLastname());
                k.setGlosow(0);
                result.getKandydatList().add(k);
            }
            return Response.ok().entity(result).build();
        }

        return dashboardEjb.wynik();
    }

}
