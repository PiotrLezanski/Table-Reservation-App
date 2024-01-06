package com.example.application.UI.customer;

import com.example.application.backend.Restaurant;
import com.example.application.backend.RestaurantService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.util.Base64;

@PageTitle("restaurants")
@Route(value = "restaurants", layout = MainMenuView.class)
@PermitAll
public class RestaurantListView extends VerticalLayout
{
    private Grid<Restaurant> grid = new Grid<>(Restaurant.class);
    private RestaurantService restaurantService;
    
    public RestaurantListView(RestaurantService restaurantService)
    {
        this.restaurantService = restaurantService;
        
        setSizeFull();
        configureGrid();
        
        addImageColumn();
        
        add(grid);
        updateList();
    }
    
    private void configureGrid()
    {
        grid.setSizeFull();
        grid.setColumns("name", "address", "type");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void updateList()
    {
        grid.setItems(restaurantService.findAll());
    }

    // delete after database connection
    final String image = "R0lGODlhOgA5APcAAAAAAAAAMwAAZgAAmQAAzAAA/wArAAArMwArZgArmQArzAAr/wBVAABVMwBVZgBVmQBVzABV/wCAAACAMwCAZgCAmQCAzACA/wCqAACqMwCqZgCqmQCqzACq/wDVAADVMwDVZgDVmQDVzADV/wD/AAD/MwD/ZgD/mQD/zAD//zMAADMAMzMAZjMAmTMAzDMA/zMrADMrMzMrZjMrmTMrzDMr/zNVADNVMzNVZjNVmTNVzDNV/zOAADOAMzOAZjOAmTOAzDOA/zOqADOqMzOqZjOqmTOqzDOq/zPVADPVMzPVZjPVmTPVzDPV/zP/ADP/MzP/ZjP/mTP/zDP//2YAAGYAM2YAZmYAmWYAzGYA/2YrAGYrM2YrZmYrmWYrzGYr/2ZVAGZVM2ZVZmZVmWZVzGZV/2aAAGaAM2aAZmaAmWaAzGaA/2aqAGaqM2aqZmaqmWaqzGaq/2bVAGbVM2bVZmbVmWbVzGbV/2b/AGb/M2b/Zmb/mWb/zGb//5kAAJkAM5kAZpkAmZkAzJkA/5krAJkrM5krZpkrmZkrzJkr/5lVAJlVM5lVZplVmZlVzJlV/5mAAJmAM5mAZpmAmZmAzJmA/5mqAJmqM5mqZpmqmZmqzJmq/5nVAJnVM5nVZpnVmZnVzJnV/5n/AJn/M5n/Zpn/mZn/zJn//8wAAMwAM8wAZswAmcwAzMwA/8wrAMwrM8wrZswrmcwrzMwr/8xVAMxVM8xVZsxVmcxVzMxV/8yAAMyAM8yAZsyAmcyAzMyA/8yqAMyqM8yqZsyqmcyqzMyq/8zVAMzVM8zVZszVmczVzMzV/8z/AMz/M8z/Zsz/mcz/zMz///8AAP8AM/8AZv8Amf8AzP8A//8rAP8rM/8rZv8rmf8rzP8r//9VAP9VM/9VZv9Vmf9VzP9V//+AAP+AM/+AZv+Amf+AzP+A//+qAP+qM/+qZv+qmf+qzP+q///VAP/VM//VZv/Vmf/VzP/V////AP//M///Zv//mf//zP///wAAAAAAAAAAAAAAACH5BAEAAPwALAAAAAA6ADkAAAj/AIlp0iRQGcGBBgUeTAiqIMGGAzVlikix4kBiDTNi3GjQIkRNyjIaHAny4cOEE4dJJJYSVCZin2BejDlyJKiHAl8iPAiRZMJPOEFmSmMwo6abCilmMpoM47CPFF0qhJipo6anJZVNBJopE5quG5OBBCVWa8eJMjUCJbj04MCJEyNW3VmSJaY0mCZ17Up3Y8OYoLCyvIoQFMOdHQdjHKiy6iRMkiJL4oWJUd6JYudO9DlQbMaXAjEqc5jwaMmVpiWJUS1MUpjWYXhJ1ulzs8SzEiMmhnjTYMyVRjE+ltx6GOXWrSVlLdlwac6cBsUeXVwXqdGOgSVOFsZumDBh84R5/2+NaVjVwM2tH217evdiktPhah0I+Xt4SfPyf/c+yS9Jpz0RtpRGBCrknGmJacfdMPIkww4y88jTjjCUlbVcSKI9hdFHVqln0UGAdcWLMJjMw2Ay+Y0nHkGAYWQhZ09NtIlpA/Vk1E03SaSXJOUJkwyK+eUzD4TDmNeRWRcKRZ1HNUYlVyaSSSKeePM8M8+PJqKoEno4LRUjjlc1BMcmY46pyYxnHoXmTWhIhomP3s0jZH5aIvPjMEdelxUxTRkFx0B/BiroQGhOwiNl3mFZoj750JOPlsls9pJL2Pl11JYzjmnHmX9uAkocnAZq6Hc9Dpllo3Mmg8wwgGnyG4uDAf+1pUJmahIHKGVq8uemmtwhkXjDJOPjqlcGiaKWmORU41ymCXQTXJ8eBaqupsERykDTdlUeMpFlMs8lxs6DiZCPmjfdR166GuaS1EZL5lF/XjtmJkXCaV+wc4ZHj36RNomjs03mptK113pqWsG7wotGoj1aGd6P+Qwj5J3CoIFGRGA22xuNm266iR3vwgGKp2hqgkl5rB0rj5xyogihsJmId5e/Af87siYF53iTyLretKkwMaMRhrARAkkunVeKl4x3krwh8sembQzmyJte+0Ymk7yh5rPeDSPGGcO8nF+QY78ctrDJlMfInyNPfTDOpnUl2WMYmxxsGAv7yPLRDqP/rfSU3mmNI8lt49j2JBRCJlncwc5zhpTJrIxiqqai6OPfwpaHyRueGr71taC4FKx5Mh/lUuZioPEdipKzPPaPUwobLOxFXuwpwaGb7unsfwvDNXjDPO5j6y0Xa/mPficadrADml536MH6CHNkaIixLSar7fcykEhf3s6qq6q6vLBF4W6xGBOpvnRyZ1gsSSSYsCOs9T1OGT4ywjx6pSRLyxO9iZdLRkgMYxhNiCEMPcAb2JaGifa5CXIVu0H2SKQ4xfFodG9a1baWF76nFAMUoLvBDcJwA6FZLi+QKc8wMBEz7IWBR0CLhGowsYg2EQcTlIlZ7MRjJzyFroBuGOEI/8Nwhit5500thMwRDxiJFSYnMg2EIolWGDMerQpwwiJgSDSBBhGGAQdhwITsNAc08UgJMu07GXlkaMEUOrGMKzyZHMUzqZB45QYx8CL/aAfHFEYCDWx8XxhTyCMYFqeNw7CMdyiTQhaap4sxCAMRSxQeKpIoaJh44eMkgYYzhPGQZ4yZd0SpxpOtMFiSwODJSEjCEYpxaRpcYWTOAL+TSYKWQosMib7TwiN654qj+9GbTAksyLDSlUlr4S3P8DjFtQlvf/TkGcjYIx3C8nL78VF57vQmMQxxhN6CHSYiwUwpPa5NncSlaxzYzCkig4VlZCDaZLe0ZECDRJIQ4gj1hv+JS3ztfZiwoScjsZpZRumWqQQc+BLFS/BlIhlowEQ7MmFML95geZEgJzkzEQlGXEJogAzpOWXoyQtiAhkxQ5sYFdq1YYSBokwbYR6nectIXAKhtwTkAQu5SZ3iDaFvGp/SNLjStKXtpavLZx5HqJo/RjGMCG0mOnMaTU1aJqj4g2WwUui3TITBenGUoAStRyKoRtQrMpTMGcSQVoq6JqBAXd4nshnDPVJQkm+SkhhwIIbUuXWvX8Rb6iDTppJGNDI/vSUj2mRL6gX0TRbb5jA4uZoXWuwHFrMhJ9d6QKF9DW9fJWJoE8tJg3ayk+tMXRgWwaO8tq+GB0wdDsaAhjT/cLJ658Pt1z4b2vZJ0pO/DS1bY7tWSe72hTBsJQn5itvSqka3ID3uCItLwgN68mvotOwfqZfGQnoWDYxYKw7Gi4OvODOnuPWk0ET7WaEVV2gEPUMNQarJZ972tuisXnn7qjrC/pGtnQzvZ61rXEmKVoHoPMNiS4uGGjq3TanjpIBvAMav8AjCa3VNJ796hiFumJm7XStBt/vHEheWu/fN7F6r5wMozhK31VPweo872rWK+MRTteFUqffc54bBB2PYL2Fhu1aLgdaT0/2aNAmcywaH1MQlZnBmVSwGH6CBtgwWLEjpe8ADtxe0232waiSc2SIvNnV9xZt+0Yzm79IXV8nqZaaB3dtecgKYpI+T8XPbHGNmtoHLEqywcMGIXSKGuIaavOVq2rdg8J4TvxabRPVuQEuNnuESZ2hDJDTtBnL6dq3TBS1onQxaTr5QgTaOcZNTp9uAAAA7";
    private static String TRANSPARENT_GIF_1PX =
            "data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACwAAAAAAQABAAACAkQBADs=";
    
    private void addImageColumn()
    {
        grid.addColumn(
                LitRenderer
                        .<Restaurant>of(
                                "<div><img style='height: 80px; width: 80px;' src=${item.imagedata} alt=${item.name}></div>"
                        )
                        .withProperty("imagedata", item -> getImageAsBase64(Base64.getDecoder().decode(image)))
                        .withProperty("name", item -> "item.getFirstName() + " + " + item.getLastName()")
        );
    }

    

    private String getImageAsBase64(byte[] string) {
        String mimeType = "image/png";
        String htmlValue = null;
        if (string == null) htmlValue = TRANSPARENT_GIF_1PX; else htmlValue =
                "data:" + mimeType + ";base64," + Base64.getEncoder().encodeToString(string);
        return htmlValue;
    }
}
