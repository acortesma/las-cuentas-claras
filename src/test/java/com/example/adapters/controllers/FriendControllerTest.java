// package com.example.adapters.controllers;
//
// import com.example.entities.Friend;
// import com.example.use.cases.ports.input.FriendService;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.context.annotation.Import;
// import org.springframework.test.web.servlet.MockMvc;
//
// import java.util.List;
//
// import static org.hamcrest.Matchers.hasSize;
// import static org.hamcrest.Matchers.is;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.BDDMockito.given;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
// @WebMvcTest(controllers = FriendController.class)
// @Import(FriendToFriendWebResponseConverter.class)
// class FriendControllerTest {
//
//  @Autowired private MockMvc mockMvc;
//
//  @MockBean private FriendService albumService;
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
//  void givenNotFilterReturnAllAlbumsWith200() throws Exception {
//
//    given(albumService.retrieveFriends(any())).willReturn(ALBUMS_TEST);
//
//    mockMvc
//        .perform(get("/collection/all"))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$", hasSize(ALBUMS_TEST.size())))
//        .andExpect(jsonPath("$[0].id", is(ALBUMS_TEST.get(0).getId())));
//  }
//
//  @Test
//  void givenNotExistAlbumsReturnEmptyWith200() throws Exception {
//
//    given(albumService.retrieveFriends(any())).willReturn(List.of());
//
//    mockMvc
//        .perform(get("/collection/all"))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$", hasSize(0)));
//  }
// }
