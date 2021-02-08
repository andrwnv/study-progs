; Task 1.
(defun task-1 ()
    (setq x (list 9 8 (list 7 6 5) 'e 'w))
    (format t "Task 1 ~%[Result] = ~d ~%~%" (nth 0 (nth 2 x)))
)


; Task 2.
(defun task-2 (_list)
    (if ((lambda (_item1 _item2) (or (numberp _item1) (numberp _item2))) (nth 1 _list) (nth 2 _list))
        (return-from task-2 T))

    (return-from task-2 NIL)
)


; Task 3.
(defun rotate (_list)
    (rotatef (nth 0 _list) (nth (- (length _list) 1) _list))
)

(defun task-3 (_list)
    (setq _new_list _list)

    (setq _last (nth 0 (last _new_list)))
    (setq _first (nth 0 _new_list))

    (format t "Task 3 ~%List before = ~d ~%" _new_list)

    (cond ((= _first _last)
        (setq _new_list (remove _first _new_list :count 1))
        (setq _new_list (remove _last _new_list :count 1 :from-end t)))
        ((rotate _new_list))
    )
    
    (return-from task-3 _new_list)
)


; Tasks run.
(defun require-input ()
    (format t "Input task number [4 for leave]: ~%")
    (setq inp_res (read))

    (if (or (> inp_res 4) (< inp_res 0))
        (return-from require-input 4))

    (return-from require-input inp_res)
)

(setq list1 (list 9 5 1 2 2 2))
(setq list2 (list 1 2 3 4 5 6 1 1 1 1))

(setq list3 (list 1 2 3 4 5))
(setq list4 (list 1 "q" "q" 4 5))
(setq list5 (list 1 "q" 3 4 5))


(loop 
    (case (require-input)
        (1 (task-1))
        
        (2 (format t "Task 2 [LIST ~d] Result = ~d ~%"   list3 (task-2 list3))
        (format t "Task 2 [LIST ~d] Result = ~d ~%"   list4 (task-2 list4))
        (format t "Task 2 [LIST ~d] Result = ~d ~%~%" list5 (task-2 list5)))
        
        (3 (format t "Result list 1 = ~d ~%~%" (task-3 list1))
        (format t "Result list 1 = ~d ~%~%" (task-3 list2))) 
        
        (4 (return))
    )
)
