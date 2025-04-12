package like_lion.pangjam.Controller;

import like_lion.pangjam.Dto.LocationDto;
import like_lion.pangjam.Service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    // location 생성
    @PostMapping
    public ResponseEntity<LocationDto.Response.Create> createLocation(@RequestBody LocationDto.Request.Create locationDto) {
        LocationDto.Response.Create createdLocation = locationService.createLocation(locationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLocation);
    }

    //location 검색
    @GetMapping
    public ResponseEntity<LocationDto.Response> getLocations(
            @RequestParam(required = false) String placeName,
            @RequestParam(required = false) String categoryGroupName,
            @RequestParam(required = false) String categoryName) {

        LocationDto.Response locations = null;

        if (placeName != null) {
            locations = locationService.getLocationByPlaceName(placeName);
        }
        else if (categoryGroupName != null) {
            locations = locationService.getLocationByCategoryGroupName(categoryGroupName);
        }
        else if (categoryName != null) {
            locations = locationService.getLocationByCategoryName(categoryName);
        }

        if(locations == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(locations);
    }

    // location 상세 정보 조회
    @GetMapping("/{locationId}")
    public ResponseEntity<LocationDto.Response.Detail> getLocationDetail(@PathVariable int locationId) {
        LocationDto.Response.Detail locationDetail = locationService.getLocationDetail(locationId);
        if (locationDetail == null) {
            return ResponseEntity.notFound().build(); // HTTP 404 반환
        }
        return ResponseEntity.ok(locationDetail); // HTTP 200 반환
    }
}
