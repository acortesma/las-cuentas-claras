// package com.example.use.cases.interactors;
//
// import com.example.entities.Friend;
// import com.example.use.cases.ports.output.FriendRepository;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
//
// import java.util.List;
//
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.BDDMockito.given;
//
// @ExtendWith(SpringExtension.class)
// class AlbumServiceImplTest {
//
//  @InjectMocks private AlbumServiceImpl albumService;
//
//  @Mock private FriendRepository repository;
//
//  private static final List<Friend> ALBUMS_TEST =
//      List.of(
//          Friend.builder()
//              .id("9454911")
//              .title("––BASKETBALL")
//              .description(null)
//              .coverPhotoId("8hW2ZB4OHZ0")
//              .build(),
//          Friend.builder()
//              .id("11987944")
//              .title("The Civil Rights Movement in Color")
//              .description("These images are colorized by Jordan")
//              .coverPhotoId("U2F-bYmuEqU")
//              .build());
//
//  @Test
//  void given_emptyCollectionAlbumAndNotFilterReturnListEmpty() {
//
//    given(repository.getModelPersistence()).willReturn(List.of());
//
//    var result = albumService.retrieveFriends(null);
//
//    assertEquals(result, List.of());
//  }
//
//  @Test
//  void given_emptyCollectionAlbumAndFilterReturnListEmpty() {
//
//    given(repository.getModelPersistence()).willReturn(List.of());
//
//    var result = albumService.retrieveFriends("test");
//
//    assertEquals(List.of(), result);
//  }
//
//  @Test
//  void given_collectionAlbumAndNotFilterReturnAllAlbums() {
//
//    given(repository.getModelPersistence()).willReturn(ALBUMS_TEST);
//
//    var result = albumService.retrieveFriends(null);
//
//    assertEquals(ALBUMS_TEST, result);
//  }
//
//  @Test
//  void given_collectionAlbumAndFilterReturnFilterAlbum() {
//
//    given(repository.getModelPersistence()).willReturn(ALBUMS_TEST);
//
//    var result = albumService.retrieveFriends(ALBUMS_TEST.get(0).getId());
//
//    assertEquals(1, result.size());
//    assertEquals(ALBUMS_TEST.get(0), result.get(0));
//  }
// }
