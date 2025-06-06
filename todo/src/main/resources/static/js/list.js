// checkbox 클릭이 되면
// checkbox value, data-id 가져오기

// 이벤트버블링 (자식의 이벤트가 부모한테 전달)
document.querySelector(".list-group").addEventListener("click", (e) => {
  // 어떤 label 안 checkbox 에서 이벤트가 발생했는지 확인
  const chk = e.target;
  console.log(chk);
  // checkbox 체크, 해제 여부확인
  console.log(chk.checked);

  // id 가져오기
  // closest("선택자") : 부모에게 제일 가까운 요소 찾기
  // data-* 속성값 가져오기 : dataset
  const id = chk.closest("label").dataset.id; // data- 다음 오는 이름을 dataset 뒤에 붙인다
  console.log(id);

  //   actionFrom 찾은 후 값 변경하기
  const actionFrom = document.querySelector("#actionForm");
  actionFrom.id.value = id;
  actionFrom.completed.value = chk.checked;

  actionFrom.submit();
});
