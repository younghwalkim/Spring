// Get chatbot elements
const chatbot = document.getElementById('chatbot');
const conversation = document.getElementById('conversation');
const inputForm = document.getElementById('input-form');
const inputField = document.getElementById('input-field');

// Add event listener to input form
inputForm.addEventListener('submit', function(event) {
  // Prevent form submission
  event.preventDefault();

  // Get user input
  const input = inputField.value;

  // Clear input field
  inputField.value = '';
  const currentTime = new Date().toLocaleTimeString([], { hour: '2-digit', minute: "2-digit" });

  // Add user input to conversation
  let message = document.createElement('div');
  message.classList.add('chatbot-message', 'user-message');
  message.innerHTML = `<p class="chatbot-text" sentTime="${currentTime}">${input}</p>`;
  conversation.appendChild(message);

  // Generate chatbot response
  const response = generateResponse(input);

  // Add chatbot response to conversation
  message = document.createElement('div');
  message.classList.add('chatbot-message','chatbot');
  message.innerHTML = `<p class="chatbot-text" sentTime="${currentTime}">${response}</p>`;
  conversation.appendChild(message);
  message.scrollIntoView({behavior: "smooth"});
});


// Generate chatbot response function
function generateResponse(input) {
    
// 특정 키워드에 따라 다른 응답 생성
  if (input.includes('안녕')) {
      return "안녕하세요! 만나서 반가워요! 😊";
  } else if (input.includes('날씨')) {
      return "오늘 날씨는 맑습니다. 즐거운 하루 되세요! 🌞";
  } else if (input.includes('음식')) {
      return "제가 좋아하는 음식은 피자입니다. 당신에게 추천드려요~";
  } else if (input.includes('가입')) {
      const response = "가입에 대해서 문의하셨나요?<br> " 
      		+ "<a href=''>1. 회원가입에 대해 답변.</a> <br> "
      		+ "<a href='#'>2. 팀원가입에 대해 답변.</a> <br> "
      		+ "<a href='#'>3. 회원탈퇴에 대해 답변.</a> <br> ";
      
      return response; 
/*  
  } else if (input.includes('게시물')){
      const response = {
          text: "어떤 게시물로 이동을 원하시나요?",
          options: [
              { text: "내 게시물 보기", next: "" },
              { text: "게시물 수정", next: "" }
          ]
      };
      return response;
*/

  } else {   
      const responses = [
          "죄송해요, 답변 준비중입니다.  😕",
          "무엇을 도와드릴까요?",
          "어떤 문제든지 해결해 드릴게요! 🚀",
          // 더 많은 응답 추가 가능
      ];
      return responses[Math.floor(Math.random() * responses.length)];
  }
  
}
 
  
