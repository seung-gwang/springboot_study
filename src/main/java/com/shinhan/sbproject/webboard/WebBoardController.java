package com.shinhan.sbproject.webboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/webboard")
@Slf4j
public class WebBoardController {
	final WebBoardRepository boardRepo;
	
	@GetMapping("/list.do")
	public void f1(Model model) {
		model.addAttribute("blist", boardRepo.findAll());
	}
	
	@GetMapping("/detail.do")
	public void f1(Model model, Long bno) {
		model.addAttribute("board", boardRepo.findById(bno).orElse(null));
	}
	
	@GetMapping("/update.do")
	public String f3(WebBoard board, RedirectAttributes attr) {
		log.info(board.toString());
		attr.addFlashAttribute("message", "수정했따!");
		boardRepo.save(board);
		return "redirect:/webboard/list.do";
	}
	
	@GetMapping("/insert.do")
	public void f4() {
		
	}
	
	@PostMapping("/insert.do")
	public String f5(WebBoard board, RedirectAttributes attr) {
		WebBoard newBoard = boardRepo.save(board);
		
		String message = "입력됨";
		if(newBoard == null) {
			message = "입력 실패 ㄷㄷ";
		}
		attr.addFlashAttribute("message", message);
		return "redirect:/webboard/list.do";
	}
	
	@GetMapping("/delete.do")
	public String f6(Long bno, RedirectAttributes attr) {
		boardRepo.deleteById(bno);
		attr.addFlashAttribute("message", "삭제 성공함ㅋ");
		return "redirect:/webboard/list.do";
	}
	
	
	
}
