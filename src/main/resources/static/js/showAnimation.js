document.querySelectorAll('.animated').forEach(elem => {
    const observer = new IntersectionObserver(entries => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                elem.classList.add('show_transition');
                return;
            }
            elem.classList.remove('show_transition');
        });
    });

    observer.observe(elem)
});